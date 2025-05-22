package com.pudutech.rgbdviewer;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.rgbdlib.RGBDData;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.RobotType;
import com.pudutech.rgbdviewer.RGBDView;
import com.pudutech.widget.loading.CLoadingView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010$\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0006\u0010'\u001a\u00020\u000bJ\b\u0010(\u001a\u00020 H\u0002J\u0010\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010,\u001a\u00020 2\u0006\u0010*\u001a\u00020+J\b\u0010-\u001a\u00020 H\u0016J\u0012\u0010.\u001a\u00020 2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020 H\u0014J\b\u00102\u001a\u00020 H\u0014J\b\u00103\u001a\u00020 H\u0002J\u0010\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020\u000bH\u0002J\u0006\u00106\u001a\u00020 J\u000e\u00107\u001a\u00020 2\u0006\u00108\u001a\u00020\u000bJ\u0010\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020\u0004H\u0002J\u0006\u0010;\u001a\u00020 J\u0018\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010>\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015X\u0082.¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, m3961d2 = {"Lcom/pudutech/rgbdviewer/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "VERSION", "centerView", "Lcom/pudutech/rgbdviewer/RGBDView;", "downRGBDCheckBitmap", "Landroid/graphics/Bitmap;", "enable_auto_detect", "", "errorCheckBitmap", "inited", "isNeedFinish", "leftView", "mHomeWatcherReceiver", "Lcom/pudutech/rgbdviewer/MainActivity$HomeWatcherReceiver;", "rgbdSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "rgbdViews", "", "[Lcom/pudutech/rgbdviewer/RGBDView;", "rightView", "robotType", "Lcom/pudutech/rgbdlib/RobotType;", "getRobotType", "()Lcom/pudutech/rgbdlib/RobotType;", "setRobotType", "(Lcom/pudutech/rgbdlib/RobotType;)V", "switch", "dealKnownPosData", "", "rgbdData", "Lcom/pudutech/rgbdlib/RGBDData;", "dealUnknowPosData", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "downRgbdConfigExist", "exitAPP1", "jump2Robot", "context", "Landroid/content/Context;", "killMirsdk", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStop", "registerReceiver", "saveConfig", "popupWindow", "saveErrorDetectPic", "setBackFinish", "flag", "setPromoteText", NotificationCompat.CATEGORY_MESSAGE, "showDownRGBDCheck", "showView", "view", "swapSerialNum", "HomeWatcherReceiver", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MainActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private RGBDView centerView;
    private Bitmap downRGBDCheckBitmap;
    private Bitmap errorCheckBitmap;
    private boolean inited;
    private boolean isNeedFinish;
    private RGBDView leftView;
    private HomeWatcherReceiver mHomeWatcherReceiver;
    private RGBDSensor rgbdSensor;
    private RGBDView[] rgbdViews;
    private RGBDView rightView;
    private boolean switch;
    private boolean enable_auto_detect = true;
    private final String VERSION = "V1.0.22";
    private final String TAG = "RGBDView";
    private RobotType robotType = RobotType.Hls;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RobotType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[RobotType.Bellabot.ordinal()] = 1;
            $EnumSwitchMapping$0[RobotType.Hls.ordinal()] = 2;
            $EnumSwitchMapping$0[RobotType.RecycleDog.ordinal()] = 3;
            $EnumSwitchMapping$0[RobotType.Ninetales.ordinal()] = 4;
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    public static final /* synthetic */ RGBDView access$getCenterView$p(MainActivity mainActivity) {
        RGBDView rGBDView = mainActivity.centerView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerView");
        }
        return rGBDView;
    }

    public static final /* synthetic */ Bitmap access$getDownRGBDCheckBitmap$p(MainActivity mainActivity) {
        Bitmap bitmap = mainActivity.downRGBDCheckBitmap;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downRGBDCheckBitmap");
        }
        return bitmap;
    }

    public static final /* synthetic */ Bitmap access$getErrorCheckBitmap$p(MainActivity mainActivity) {
        Bitmap bitmap = mainActivity.errorCheckBitmap;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorCheckBitmap");
        }
        return bitmap;
    }

    public static final /* synthetic */ RGBDView access$getLeftView$p(MainActivity mainActivity) {
        RGBDView rGBDView = mainActivity.leftView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        return rGBDView;
    }

    public static final /* synthetic */ RGBDSensor access$getRgbdSensor$p(MainActivity mainActivity) {
        RGBDSensor rGBDSensor = mainActivity.rgbdSensor;
        if (rGBDSensor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        return rGBDSensor;
    }

    public static final /* synthetic */ RGBDView access$getRightView$p(MainActivity mainActivity) {
        RGBDView rGBDView = mainActivity.rightView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        return rGBDView;
    }

    public final RobotType getRobotType() {
        return this.robotType;
    }

    public final void setRobotType(RobotType robotType) {
        Intrinsics.checkParameterIsNotNull(robotType, "<set-?>");
        this.robotType = robotType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.pudutech.rgbdviewer.MainActivity] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = this;
        killMirsdk(mainActivity);
        registerReceiver();
        Pdlog.init(mainActivity, "RgbdViewer", true, this.VERSION, null);
        setContentView(2131427356);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this;
        Button jumpToRbotButton = (Button) findViewById(2131230723);
        Button saveButton = (Button) findViewById(2131230968);
        Button switchButton = (Button) findViewById(2131231016);
        Button errorCheckButton = (Button) findViewById(2131230881);
        Button button = (Button) findViewById(2131230870);
        Intrinsics.checkExpressionValueIsNotNull(jumpToRbotButton, "jumpToRbotButton");
        jumpToRbotButton.setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(saveButton, "saveButton");
        saveButton.setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(switchButton, "switchButton");
        switchButton.setVisibility(8);
        Intrinsics.checkExpressionValueIsNotNull(errorCheckButton, "errorCheckButton");
        errorCheckButton.setVisibility(8);
        FrameLayout downCheckLayout = (FrameLayout) _$_findCachedViewById(C5692R.id.downCheckLayout);
        Intrinsics.checkExpressionValueIsNotNull(downCheckLayout, "downCheckLayout");
        downCheckLayout.setVisibility(8);
        Button pic_change = (Button) _$_findCachedViewById(C5692R.id.pic_change);
        Intrinsics.checkExpressionValueIsNotNull(pic_change, "pic_change");
        pic_change.setVisibility(8);
        Button pic_change1 = (Button) _$_findCachedViewById(C5692R.id.pic_change1);
        Intrinsics.checkExpressionValueIsNotNull(pic_change1, "pic_change1");
        pic_change1.setVisibility(8);
        View findViewById = findViewById(2131230878);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayout>(R.id.errorCheckLayout)");
        ((LinearLayout) findViewById).setVisibility(8);
        View findViewById2 = findViewById(2131230867);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayou…R.id.downRGBDCheckLayout)");
        ((LinearLayout) findViewById2).setVisibility(8);
        ((TextView) findViewById(2131231096)).setText(this.VERSION);
        this.rgbdSensor = new RGBDSensor(mainActivity);
        Bitmap createBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(1000…Bitmap.Config.ARGB_8888 )");
        this.errorCheckBitmap = createBitmap;
        RGBDSensor rGBDSensor = this.rgbdSensor;
        if (rGBDSensor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor.setErrorCheckCB(new Function1<byte[], Unit>() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                invoke2(bArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final byte[] resultData) {
                String str;
                Intrinsics.checkParameterIsNotNull(resultData, "resultData");
                try {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MainActivity.access$getErrorCheckBitmap$p(MainActivity.this).copyPixelsFromBuffer(ByteBuffer.wrap(resultData).rewind());
                            ((ImageView) MainActivity.this.findViewById(2131230880)).setImageBitmap(MainActivity.access$getErrorCheckBitmap$p(MainActivity.this));
                        }
                    });
                } catch (Exception e) {
                    str = MainActivity.this.TAG;
                    Pdlog.m3274e(str, "exception: " + e.toString());
                }
            }
        });
        View findViewById3 = findViewById(2131230868);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById<Button>(R.id.downRGBDCheckOK)");
        ((Button) findViewById3).setVisibility(8);
        Bitmap createBitmap2 = Bitmap.createBitmap(DimensionsKt.XHDPI, DimensionsKt.HDPI, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap2, "Bitmap.createBitmap(320,…Bitmap.Config.ARGB_8888 )");
        this.downRGBDCheckBitmap = createBitmap2;
        RGBDSensor rGBDSensor2 = this.rgbdSensor;
        if (rGBDSensor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor2.setDownRGBDCheckCB(new MainActivity$onCreate$2(this, objectRef));
        errorCheckButton.setOnClickListener(new MainActivity$onCreate$3(this));
        button.setOnClickListener(new MainActivity$onCreate$4(this));
        jumpToRbotButton.setOnClickListener(new MainActivity$onCreate$5(this, objectRef));
        saveButton.setOnClickListener(new MainActivity$onCreate$6(this));
        ((CLoadingView) _$_findCachedViewById(C5692R.id.loading_viewer)).postDelayed(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$7
            @Override // java.lang.Runnable
            public final void run() {
                ((CLoadingView) MainActivity.this.findViewById(2131230921)).show();
            }
        }, 100L);
        View findViewById4 = findViewById(2131230960);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "findViewById(R.id.rightDepthView)");
        ImageView imageView = (ImageView) findViewById4;
        View findViewById5 = findViewById(2131230962);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "findViewById(R.id.rightIRView)");
        ImageView imageView2 = (ImageView) findViewById5;
        View findViewById6 = findViewById(2131230963);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "findViewById(R.id.rightSerialNum)");
        TextView textView = (TextView) findViewById6;
        View findViewById7 = findViewById(2131230961);
        Intrinsics.checkExpressionValueIsNotNull(findViewById7, "findViewById(R.id.rightDescribe)");
        this.leftView = new RGBDView(imageView, imageView2, textView, (TextView) findViewById7, 848, DimensionsKt.XXHDPI, mainActivity);
        View findViewById8 = findViewById(2131230911);
        Intrinsics.checkExpressionValueIsNotNull(findViewById8, "findViewById(R.id.leftDepthView)");
        ImageView imageView3 = (ImageView) findViewById8;
        View findViewById9 = findViewById(2131230913);
        Intrinsics.checkExpressionValueIsNotNull(findViewById9, "findViewById(R.id.leftIRView)");
        ImageView imageView4 = (ImageView) findViewById9;
        View findViewById10 = findViewById(2131230914);
        Intrinsics.checkExpressionValueIsNotNull(findViewById10, "findViewById(R.id.leftSerialNum)");
        TextView textView2 = (TextView) findViewById10;
        View findViewById11 = findViewById(2131230912);
        Intrinsics.checkExpressionValueIsNotNull(findViewById11, "findViewById(R.id.leftDescribe)");
        this.rightView = new RGBDView(imageView3, imageView4, textView2, (TextView) findViewById11, 848, DimensionsKt.XXHDPI, mainActivity);
        View findViewById12 = findViewById(2131230830);
        Intrinsics.checkExpressionValueIsNotNull(findViewById12, "findViewById(R.id.centerDepthView)");
        ImageView imageView5 = (ImageView) findViewById12;
        View findViewById13 = findViewById(2131230832);
        Intrinsics.checkExpressionValueIsNotNull(findViewById13, "findViewById(R.id.centerIRView)");
        ImageView imageView6 = (ImageView) findViewById13;
        View findViewById14 = findViewById(2131230834);
        Intrinsics.checkExpressionValueIsNotNull(findViewById14, "findViewById(R.id.centerSerialNum)");
        TextView textView3 = (TextView) findViewById14;
        View findViewById15 = findViewById(2131230831);
        Intrinsics.checkExpressionValueIsNotNull(findViewById15, "findViewById(R.id.centerDescribe)");
        RGBDView rGBDView = new RGBDView(imageView5, imageView6, textView3, (TextView) findViewById15, DimensionsKt.XHDPI, DimensionsKt.HDPI, mainActivity);
        this.centerView = rGBDView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerView");
        }
        rGBDView.setState(downRgbdConfigExist() ? RGBDView.ConfigState.Configured : RGBDView.ConfigState.NotConfigured);
        RGBDView[] rGBDViewArr = new RGBDView[3];
        RGBDView rGBDView2 = this.leftView;
        if (rGBDView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        rGBDViewArr[0] = rGBDView2;
        RGBDView rGBDView3 = this.rightView;
        if (rGBDView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        rGBDViewArr[1] = rGBDView3;
        RGBDView rGBDView4 = this.centerView;
        if (rGBDView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("centerView");
        }
        rGBDViewArr[2] = rGBDView4;
        this.rgbdViews = rGBDViewArr;
        switchButton.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                synchronized (MainActivity.this) {
                    MainActivity.this.swapSerialNum();
                    Unit unit = Unit.INSTANCE;
                }
            }
        });
        ((Button) _$_findCachedViewById(C5692R.id.pic_change1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                synchronized (MainActivity.this) {
                    MainActivity.access$getLeftView$p(MainActivity.this).switchView();
                    MainActivity.access$getRightView$p(MainActivity.this).switchView();
                    MainActivity.access$getCenterView$p(MainActivity.this).switchView();
                    Unit unit = Unit.INSTANCE;
                }
            }
        });
        RGBDSensor rGBDSensor3 = this.rgbdSensor;
        if (rGBDSensor3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor3.setDirectDetectCB(new Function2<String, String, Unit>() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String s, String pos) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                Intrinsics.checkParameterIsNotNull(pos, "pos");
                if (((MainActivity.access$getLeftView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.NotConfigured) | (MainActivity.access$getRightView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.NotConfigured) | (MainActivity.access$getLeftView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.AutoDetect) | (MainActivity.access$getRightView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.AutoDetect) | (MainActivity.access$getLeftView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.AutoDetectError)) || (MainActivity.access$getRightView$p(MainActivity.this).getConfigState() == RGBDView.ConfigState.AutoDetectError)) {
                    if (Intrinsics.areEqual(pos, "Unknow")) {
                        MainActivity.access$getLeftView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetectError);
                        MainActivity.access$getRightView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetectError);
                        return;
                    }
                    if (Intrinsics.areEqual(pos, "Right")) {
                        if (!Intrinsics.areEqual(MainActivity.access$getRightView$p(MainActivity.this).getSerialNum(), s)) {
                            MainActivity.this.swapSerialNum();
                        }
                        MainActivity.access$getLeftView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetect);
                        MainActivity.access$getRightView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetect);
                        return;
                    }
                    if (Intrinsics.areEqual(pos, "Left")) {
                        if (!Intrinsics.areEqual(MainActivity.access$getLeftView$p(MainActivity.this).getSerialNum(), s)) {
                            MainActivity.this.swapSerialNum();
                        }
                        MainActivity.access$getLeftView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetect);
                        MainActivity.access$getRightView$p(MainActivity.this).setState(RGBDView.ConfigState.AutoDetect);
                    }
                }
            }
        });
        RGBDSensor rGBDSensor4 = this.rgbdSensor;
        if (rGBDSensor4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor4.setStateCB(new MainActivity$onCreate$11(this, switchButton, saveButton, jumpToRbotButton, button, objectRef));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MainActivity$onCreate$12(this, objectRef, null), 3, null);
    }

    public final void setBackFinish(boolean flag) {
        this.isNeedFinish = flag;
    }

    private final void registerReceiver() {
        this.mHomeWatcherReceiver = new HomeWatcherReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        HomeWatcherReceiver homeWatcherReceiver = this.mHomeWatcherReceiver;
        if (homeWatcherReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHomeWatcherReceiver");
        }
        registerReceiver(homeWatcherReceiver, intentFilter);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event != null) {
            Integer.valueOf(event.getKeyCode());
        }
        if (event != null && event.getAction() == 0 && event.getKeyCode() == 4) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPromoteText(final String msg) {
        runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$setPromoteText$1
            @Override // java.lang.Runnable
            public final void run() {
                ((TextView) MainActivity.this.findViewById(2131230942)).setText(msg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dealUnknowPosData(RGBDData rgbdData) {
        RGBDView rGBDView = this.leftView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        if (Intrinsics.areEqual(rGBDView.getSerialNum(), rgbdData.getSerialNum())) {
            RGBDView rGBDView2 = this.leftView;
            if (rGBDView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            showView(rGBDView2, rgbdData);
            return;
        }
        RGBDView rGBDView3 = this.rightView;
        if (rGBDView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        if (Intrinsics.areEqual(rGBDView3.getSerialNum(), rgbdData.getSerialNum())) {
            RGBDView rGBDView4 = this.rightView;
            if (rGBDView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            showView(rGBDView4, rgbdData);
            return;
        }
        RGBDView[] rGBDViewArr = this.rgbdViews;
        if (rGBDViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdViews");
        }
        for (RGBDView rGBDView5 : rGBDViewArr) {
            if (rGBDView5.getSerialNum().length() == 0) {
                rGBDView5.setState(RGBDView.ConfigState.NotConfigured);
                rGBDView5.setSerialNum(rgbdData.getSerialNum());
                showView(rGBDView5, rgbdData);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void swapSerialNum() {
        RGBDView rGBDView = this.leftView;
        if (rGBDView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        String serialNum = rGBDView.getSerialNum();
        RGBDView rGBDView2 = this.leftView;
        if (rGBDView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        rGBDView2.getConfigState();
        RGBDView rGBDView3 = this.leftView;
        if (rGBDView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        RGBDView rGBDView4 = this.rightView;
        if (rGBDView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        rGBDView3.setSerialNum(rGBDView4.getSerialNum());
        RGBDView rGBDView5 = this.rightView;
        if (rGBDView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        rGBDView5.setSerialNum(serialNum);
        RGBDView rGBDView6 = this.leftView;
        if (rGBDView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("leftView");
        }
        rGBDView6.setState(RGBDView.ConfigState.ManualSwitch);
        RGBDView rGBDView7 = this.rightView;
        if (rGBDView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rightView");
        }
        rGBDView7.setState(RGBDView.ConfigState.ManualSwitch);
    }

    public final void dealKnownPosData(RGBDData rgbdData) {
        RGBDView rGBDView;
        Intrinsics.checkParameterIsNotNull(rgbdData, "rgbdData");
        if (Intrinsics.areEqual(rgbdData.getPos(), "Center")) {
            RGBDView rGBDView2 = this.centerView;
            if (rGBDView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("centerView");
            }
            rGBDView2.setSerialNum(rgbdData.getSerialNum());
            showView(rGBDView2, rgbdData);
            return;
        }
        if (Intrinsics.areEqual(rgbdData.getPos(), "Left")) {
            RGBDView rGBDView3 = this.leftView;
            if (rGBDView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            if (!(rGBDView3.getSerialNum().length() == 0)) {
                if (this.leftView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("leftView");
                }
                if (!Intrinsics.areEqual(r0.getSerialNum(), rgbdData.getSerialNum())) {
                    RGBDView rGBDView4 = this.rightView;
                    if (rGBDView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rightView");
                    }
                    RGBDView rGBDView5 = this.leftView;
                    if (rGBDView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("leftView");
                    }
                    rGBDView4.setState(rGBDView5.getConfigState());
                    RGBDView rGBDView6 = this.rightView;
                    if (rGBDView6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rightView");
                    }
                    RGBDView rGBDView7 = this.leftView;
                    if (rGBDView7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("leftView");
                    }
                    rGBDView6.setSerialNum(rGBDView7.getSerialNum());
                }
            }
            rGBDView = this.leftView;
            if (rGBDView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
        } else {
            RGBDView rGBDView8 = this.rightView;
            if (rGBDView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            if (!(rGBDView8.getSerialNum().length() == 0)) {
                if (this.rightView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rightView");
                }
                if (!Intrinsics.areEqual(r0.getSerialNum(), rgbdData.getSerialNum())) {
                    RGBDView rGBDView9 = this.leftView;
                    if (rGBDView9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("leftView");
                    }
                    RGBDView rGBDView10 = this.rightView;
                    if (rGBDView10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rightView");
                    }
                    rGBDView9.setState(rGBDView10.getConfigState());
                    RGBDView rGBDView11 = this.leftView;
                    if (rGBDView11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("leftView");
                    }
                    RGBDView rGBDView12 = this.rightView;
                    if (rGBDView12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rightView");
                    }
                    rGBDView11.setSerialNum(rGBDView12.getSerialNum());
                }
            }
            rGBDView = this.rightView;
            if (rGBDView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
        }
        rGBDView.setState(RGBDView.ConfigState.Configured);
        rGBDView.setSerialNum(rgbdData.getSerialNum());
        showView(rGBDView, rgbdData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showView(final RGBDView view, RGBDData rgbdData) {
        if (view != null) {
            view.setData(rgbdData.getDepthData(), rgbdData.getIrData());
        }
        runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$showView$1
            @Override // java.lang.Runnable
            public final void run() {
                RGBDView rGBDView = RGBDView.this;
                if (rGBDView != null) {
                    rGBDView.show();
                }
            }
        });
    }

    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/rgbdviewer/MainActivity$HomeWatcherReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "SYSTEM_DIALOG_REASON_HOME_KEY", "", "getSYSTEM_DIALOG_REASON_HOME_KEY", "()Ljava/lang/String;", "SYSTEM_DIALOG_REASON_KEY", "getSYSTEM_DIALOG_REASON_KEY", "killMirsdk", "", "context", "Landroid/content/Context;", "onReceive", "intent", "Landroid/content/Intent;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class HomeWatcherReceiver extends BroadcastReceiver {
        private final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        public final String getSYSTEM_DIALOG_REASON_KEY() {
            return this.SYSTEM_DIALOG_REASON_KEY;
        }

        public final String getSYSTEM_DIALOG_REASON_HOME_KEY() {
            return this.SYSTEM_DIALOG_REASON_HOME_KEY;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent != null ? intent.getAction() : null;
            Pdlog.m3275i("HomeWatcherReceiver", "intentACTION = " + action);
            if (TextUtils.equals(action, "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                String stringExtra = intent != null ? intent.getStringExtra(this.SYSTEM_DIALOG_REASON_KEY) : null;
                Pdlog.m3275i("HomeWatcherReceiver", "reason = " + stringExtra);
                if (TextUtils.equals(this.SYSTEM_DIALOG_REASON_HOME_KEY, stringExtra)) {
                    killMirsdk(context);
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                    throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                }
            }
        }

        public final void killMirsdk(Context context) {
            Object systemService = context != null ? context.getSystemService("activity") : null;
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
                if ("com.pudutech.mirsdk".equals(runningAppProcessInfo.processName)) {
                    Pdlog.m3273d("HomeWatcherReceiver", "runningAppProcessInfo = " + runningAppProcessInfo.processName);
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HomeWatcherReceiver homeWatcherReceiver = this.mHomeWatcherReceiver;
        if (homeWatcherReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHomeWatcherReceiver");
        }
        if (homeWatcherReceiver != null) {
            try {
                HomeWatcherReceiver homeWatcherReceiver2 = this.mHomeWatcherReceiver;
                if (homeWatcherReceiver2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mHomeWatcherReceiver");
                }
                unregisterReceiver(homeWatcherReceiver2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveConfig(boolean popupWindow) {
        Boolean writeConfigForBella;
        String string = getResources().getString(2131558486);
        int i = WhenMappings.$EnumSwitchMapping$0[this.robotType.ordinal()];
        if (i == 1) {
            RGBDView rGBDView = this.leftView;
            if (rGBDView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            String serialNum = rGBDView.getSerialNum();
            RGBDView rGBDView2 = this.rightView;
            if (rGBDView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            writeConfigForBella = WriteJson.writeConfigForBella(serialNum, rGBDView2.getSerialNum(), "left", "right");
        } else if (i == 2) {
            RGBDView rGBDView3 = this.leftView;
            if (rGBDView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            String serialNum2 = rGBDView3.getSerialNum();
            RGBDView rGBDView4 = this.rightView;
            if (rGBDView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            writeConfigForBella = WriteJson.writeConfigForPlus(serialNum2, rGBDView4.getSerialNum(), "left", "right");
        } else if (i == 3) {
            RGBDView rGBDView5 = this.leftView;
            if (rGBDView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            String serialNum3 = rGBDView5.getSerialNum();
            RGBDView rGBDView6 = this.rightView;
            if (rGBDView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            writeConfigForBella = WriteJson.writeConfigForBella(serialNum3, rGBDView6.getSerialNum(), "left", "right");
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            RGBDView rGBDView7 = this.leftView;
            if (rGBDView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            String serialNum4 = rGBDView7.getSerialNum();
            RGBDView rGBDView8 = this.rightView;
            if (rGBDView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            writeConfigForBella = WriteJson.writeConfigForBella(serialNum4, rGBDView8.getSerialNum(), "left", "right");
        }
        if (!writeConfigForBella.booleanValue()) {
            string = getResources().getString(2131558485);
        } else {
            RGBDView rGBDView9 = this.leftView;
            if (rGBDView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            rGBDView9.setState(RGBDView.ConfigState.Configured);
            RGBDView rGBDView10 = this.rightView;
            if (rGBDView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            rGBDView10.setState(RGBDView.ConfigState.Configured);
            RGBDSensor rGBDSensor = this.rgbdSensor;
            if (rGBDSensor == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
            }
            RGBDView rGBDView11 = this.leftView;
            if (rGBDView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("leftView");
            }
            String serialNum5 = rGBDView11.getSerialNum();
            RGBDView rGBDView12 = this.rightView;
            if (rGBDView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rightView");
            }
            rGBDSensor.nativeSetSerialNum(serialNum5, rGBDView12.getSerialNum());
        }
        if (!writeConfigForBella.booleanValue() || popupWindow) {
            new AlertDialog.Builder(this).setTitle(getResources().getString(2131558500)).setMessage(string).setPositiveButton(getResources().getString(2131558457), (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump2Robot(Context context) {
        String str;
        if (this.robotType == RobotType.Bellabot) {
            str = com.pudutech.bumblebee.BuildConfig.APPLICATION_ID;
        } else if (this.robotType == RobotType.RecycleDog) {
            str = "com.pudutech.recycle.robot";
        } else {
            str = this.robotType == RobotType.Ninetales ? "com.pudutech.robot.ninetails" : "com.pudutech.pdrobot";
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return;
        }
        killMirsdk(context);
        context.startActivity(launchIntentForPackage);
        exitAPP1();
        Process.killProcess(Process.myPid());
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        killMirsdk(this);
    }

    private final void exitAPP1() {
        Log.d("ActivityManagerService", "application exitAPP1.........");
        Object systemService = getApplicationContext().getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        Iterator<ActivityManager.AppTask> it = ((ActivityManager) systemService).getAppTasks().iterator();
        while (it.hasNext()) {
            it.next().finishAndRemoveTask();
        }
        Log.d("ActivityManagerService", "application restart begin.........");
    }

    public final void killMirsdk(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            if ("com.pudutech.mirsdk".equals(runningAppProcessInfo.processName)) {
                Pdlog.m3273d(this.TAG, "runningAppProcessInfo = " + runningAppProcessInfo.processName);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    public final void saveErrorDetectPic() {
        List emptyList;
        try {
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            Regex regex = new Regex(str);
            List<String> split = regex.split(MapFilePathConfig.CONFIG_PATH, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (!(listIterator.previous().length() == 0)) {
                        emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            Object[] array = emptyList.toArray(new String[0]);
            if (array != null) {
                StringBuffer stringBuffer = new StringBuffer();
                for (String str2 : (String[]) array) {
                    stringBuffer.append(str2);
                    stringBuffer.append(File.separator);
                    File file = new File(stringBuffer.toString());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(MapFilePathConfig.CONFIG_PATH + File.separator + "rgbd_error_check_result.jpeg"));
                Bitmap bitmap = this.errorCheckBitmap;
                if (bitmap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("errorCheckBitmap");
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (IOException e) {
            Log.e(this.TAG, "exception " + e.getLocalizedMessage() + " :" + Log.getStackTraceString(e));
        }
    }

    public final boolean downRgbdConfigExist() {
        return new File(MapFilePathConfig.PRE_PARAM_PATH).exists();
    }

    public final void showDownRGBDCheck() {
        RGBDSensor rGBDSensor = this.rgbdSensor;
        if (rGBDSensor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor.setDownRgbdCheck(true);
        RGBDSensor rGBDSensor2 = this.rgbdSensor;
        if (rGBDSensor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rgbdSensor");
        }
        rGBDSensor2.startDownRGBDCheck();
        View findViewById = findViewById(2131230923);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<LinearLayout>(R.id.mainLayout)");
        ((LinearLayout) findViewById).setVisibility(8);
        View findViewById2 = findViewById(2131230867);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<LinearLayou…R.id.downRGBDCheckLayout)");
        ((LinearLayout) findViewById2).setVisibility(0);
        ((Button) findViewById(2131230868)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$showDownRGBDCheck$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.access$getRgbdSensor$p(MainActivity.this).stopDownRGBDCheck();
                MainActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$showDownRGBDCheck$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((ImageView) MainActivity.this.findViewById(2131230869)).setImageDrawable(null);
                        View findViewById3 = MainActivity.this.findViewById(2131230867);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById<LinearLayou…R.id.downRGBDCheckLayout)");
                        ((LinearLayout) findViewById3).setVisibility(8);
                        View findViewById4 = MainActivity.this.findViewById(2131230923);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "findViewById<LinearLayout>(R.id.mainLayout)");
                        ((LinearLayout) findViewById4).setVisibility(0);
                        View findViewById5 = MainActivity.this.findViewById(2131230868);
                        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "findViewById<Button>(R.id.downRGBDCheckOK)");
                        ((Button) findViewById5).setVisibility(8);
                        MainActivity.access$getCenterView$p(MainActivity.this).setState(MainActivity.this.downRgbdConfigExist() ? RGBDView.ConfigState.Configured : RGBDView.ConfigState.NotConfigured);
                    }
                });
            }
        });
    }
}
