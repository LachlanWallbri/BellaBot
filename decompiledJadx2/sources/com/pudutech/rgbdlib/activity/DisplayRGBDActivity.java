package com.pudutech.rgbdlib.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.gson.Gson;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import com.pudutech.rgbdlib.RGBDSensor;
import com.pudutech.rgbdlib.RGBDType;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: DisplayRGBDActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0001?B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u0012\u0010(\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010*H\u0015J\b\u0010+\u001a\u00020%H\u0014J\b\u0010,\u001a\u00020%H\u0014J\b\u0010-\u001a\u00020%H\u0014J\b\u0010.\u001a\u00020%H\u0002J\b\u0010/\u001a\u00020%H\u0002J\b\u00100\u001a\u00020%H\u0002J\b\u00101\u001a\u00020%H\u0002J\b\u00102\u001a\u00020%H\u0002J\b\u00103\u001a\u00020%H\u0002J\b\u00104\u001a\u00020%H\u0002J\b\u00105\u001a\u00020%H\u0002J\b\u00106\u001a\u00020%H\u0002J\b\u00107\u001a\u00020%H\u0002J\b\u00108\u001a\u00020%H\u0002J\u0010\u00109\u001a\u00020%2\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020%2\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010=\u001a\u00020%2\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010>\u001a\u00020%2\u0006\u0010:\u001a\u00020;H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R2\u0010\u0012\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0013j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0015\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0013j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R2\u0010!\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0007`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\"\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010#0\u0013j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010#`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/DisplayRGBDActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "TAG", "", "clickImage", "", "configFileName", "depthColorMap", "", "", "[[I", "depthColorTable", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "maxDepth", "minDepth", "saveMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "saveTypeMap", "sheildCenterRgbd", "", "sheildDownRgbd", "sheildLeftRgbd", "sheildRightRgbd", "shield", "showDepth", "theScope", "Lkotlinx/coroutines/CoroutineScope;", "getTheScope", "()Lkotlinx/coroutines/CoroutineScope;", "viewIdMap", "viewMap", "Landroid/widget/ImageView;", "onClick", "", "p0", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "startCenterDepthPreview", "startCenterIRPreview", "startDepthPreview", "startDownDepthPreview", "startDownIRPreview", "startIRPreview", "startLeftDepthPreview", "startLeftIRPreview", "startRightDepthPreview", "startRightIRPreview", "stopPreview", "updateCenterImage", "bitmap", "Landroid/graphics/Bitmap;", "updateDownImage", "updateLeftImage", "updateRightImage", "Companion", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DisplayRGBDActivity extends AppCompatActivity implements View.OnClickListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RGBDSensor rgbdSensor;
    private HashMap _$_findViewCache;
    private boolean sheildCenterRgbd;
    private boolean sheildDownRgbd;
    private boolean sheildLeftRgbd;
    private boolean sheildRightRgbd;
    private final String TAG = "MirRGBD";
    private final String configFileName = "/sdcard/pudu/config/rgbd.json";
    private final int minDepth = 100;
    private final int maxDepth = InternalConstant.RATE8K;
    private final ArrayList<Integer> depthColorTable = new ArrayList<>();
    private boolean showDepth = true;
    private boolean shield = true;
    private HashMap<Integer, ImageView> viewMap = new HashMap<>();
    private HashMap<Integer, Integer> viewIdMap = new HashMap<>();
    private HashMap<Integer, String> saveMap = new HashMap<>();
    private HashMap<Integer, String> saveTypeMap = new HashMap<>();
    private int clickImage = -1;
    private final CoroutineScope theScope = new CoroutineScope() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$theScope$1
        private final CoroutineContext coroutineContext = Dispatchers.getDefault();

        @Override // kotlinx.coroutines.CoroutineScope
        public CoroutineContext getCoroutineContext() {
            return this.coroutineContext;
        }
    };
    private final int[][] depthColorMap = {new int[]{0, 0, 128}, new int[]{0, 0, 128}, new int[]{0, 0, 132}, new int[]{0, 0, 137}, new int[]{0, 0, 141}, new int[]{0, 0, 146}, new int[]{0, 0, 150}, new int[]{0, 0, 155}, new int[]{0, 0, 159}, new int[]{0, 0, 164}, new int[]{0, 0, 168}, new int[]{0, 0, 173}, new int[]{0, 0, 178}, new int[]{0, 0, 182}, new int[]{0, 0, 187}, new int[]{0, 0, 191}, new int[]{0, 0, 196}, new int[]{0, 0, 200}, new int[]{0, 0, HttpStatus.SC_RESET_CONTENT}, new int[]{0, 0, 209}, new int[]{0, 0, 214}, new int[]{0, 0, 218}, new int[]{0, 0, 223}, new int[]{0, 0, 227}, new int[]{0, 0, 232}, new int[]{0, 0, 237}, new int[]{0, 0, 241}, new int[]{0, 0, 246}, new int[]{0, 0, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 4, 255}, new int[]{0, 8, 255}, new int[]{0, 12, 255}, new int[]{0, 16, 255}, new int[]{0, 20, 255}, new int[]{0, 24, 255}, new int[]{0, 28, 255}, new int[]{0, 32, 255}, new int[]{0, 36, 255}, new int[]{0, 40, 255}, new int[]{0, 44, 255}, new int[]{0, 48, 255}, new int[]{0, 52, 255}, new int[]{0, 56, 255}, new int[]{0, 60, 255}, new int[]{0, 64, 255}, new int[]{0, 68, 255}, new int[]{0, 72, 255}, new int[]{0, 76, 255}, new int[]{0, 80, 255}, new int[]{0, 84, 255}, new int[]{0, 88, 255}, new int[]{0, 92, 255}, new int[]{0, 96, 255}, new int[]{0, 100, 255}, new int[]{0, 104, 255}, new int[]{0, 108, 255}, new int[]{0, 112, 255}, new int[]{0, 116, 255}, new int[]{0, 120, 255}, new int[]{0, 124, 255}, new int[]{0, 128, 255}, new int[]{0, 132, 255}, new int[]{0, 136, 255}, new int[]{0, 140, 255}, new int[]{0, 144, 255}, new int[]{0, 148, 255}, new int[]{0, 152, 255}, new int[]{0, 156, 255}, new int[]{0, 160, 255}, new int[]{0, 164, 255}, new int[]{0, 168, 255}, new int[]{0, 172, 255}, new int[]{0, 176, 255}, new int[]{0, 180, 255}, new int[]{0, 184, 255}, new int[]{0, 188, 255}, new int[]{0, 192, 255}, new int[]{0, 196, 255}, new int[]{0, 200, 255}, new int[]{0, 204, 255}, new int[]{0, 208, 255}, new int[]{0, 212, 255}, new int[]{0, 216, 255}, new int[]{0, 220, 254}, new int[]{0, 224, 251}, new int[]{0, 228, GateControllerMsg.ControlCode.Error}, new int[]{2, 232, 244}, new int[]{6, 236, 241}, new int[]{9, DimensionsKt.HDPI, 238}, new int[]{12, 244, 235}, new int[]{15, GateControllerMsg.ControlCode.Error, 231}, new int[]{19, 252, 228}, new int[]{22, 255, 225}, new int[]{25, 255, 222}, new int[]{28, 255, 219}, new int[]{31, 255, 215}, new int[]{35, 255, 212}, new int[]{38, 255, 209}, new int[]{41, 255, HttpStatus.SC_PARTIAL_CONTENT}, new int[]{44, 255, 202}, new int[]{48, 255, 199}, new int[]{51, 255, 196}, new int[]{54, 255, 193}, new int[]{57, 255, 190}, new int[]{60, 255, 186}, new int[]{64, 255, 183}, new int[]{67, 255, 180}, new int[]{70, 255, 177}, new int[]{73, 255, 173}, new int[]{77, 255, 170}, new int[]{80, 255, 167}, new int[]{83, 255, 164}, new int[]{86, 255, 160}, new int[]{90, 255, 157}, new int[]{93, 255, 154}, new int[]{96, 255, 151}, new int[]{99, 255, 148}, new int[]{102, 255, 144}, new int[]{106, 255, 141}, new int[]{109, 255, 138}, new int[]{112, 255, 135}, new int[]{115, 255, 131}, new int[]{119, 255, 128}, new int[]{122, 255, 125}, new int[]{125, 255, 122}, new int[]{128, 255, 119}, new int[]{131, 255, 115}, new int[]{135, 255, 112}, new int[]{138, 255, 109}, new int[]{141, 255, 106}, new int[]{144, 255, 102}, new int[]{148, 255, 99}, new int[]{151, 255, 96}, new int[]{154, 255, 93}, new int[]{157, 255, 90}, new int[]{160, 255, 86}, new int[]{164, 255, 83}, new int[]{167, 255, 80}, new int[]{170, 255, 77}, new int[]{173, 255, 73}, new int[]{177, 255, 70}, new int[]{180, 255, 67}, new int[]{183, 255, 64}, new int[]{186, 255, 60}, new int[]{190, 255, 57}, new int[]{193, 255, 54}, new int[]{196, 255, 51}, new int[]{199, 255, 48}, new int[]{202, 255, 44}, new int[]{HttpStatus.SC_PARTIAL_CONTENT, 255, 41}, new int[]{209, 255, 38}, new int[]{212, 255, 35}, new int[]{215, 255, 31}, new int[]{219, 255, 28}, new int[]{222, 255, 25}, new int[]{225, 255, 22}, new int[]{228, 255, 19}, new int[]{231, 255, 15}, new int[]{235, 255, 12}, new int[]{238, 255, 9}, new int[]{241, 252, 6}, new int[]{244, GateControllerMsg.ControlCode.Error, 2}, new int[]{GateControllerMsg.ControlCode.Error, 245, 0}, new int[]{251, 241, 0}, new int[]{254, 237, 0}, new int[]{255, 234, 0}, new int[]{255, 230, 0}, new int[]{255, 226, 0}, new int[]{255, 222, 0}, new int[]{255, 219, 0}, new int[]{255, 215, 0}, new int[]{255, Primes.SMALL_FACTOR_LIMIT, 0}, new int[]{255, 208, 0}, new int[]{255, 204, 0}, new int[]{255, 200, 0}, new int[]{255, 196, 0}, new int[]{255, 193, 0}, new int[]{255, 189, 0}, new int[]{255, 185, 0}, new int[]{255, 182, 0}, new int[]{255, 178, 0}, new int[]{255, 174, 0}, new int[]{255, 171, 0}, new int[]{255, 167, 0}, new int[]{255, 163, 0}, new int[]{255, 159, 0}, new int[]{255, 156, 0}, new int[]{255, 152, 0}, new int[]{255, 148, 0}, new int[]{255, 145, 0}, new int[]{255, 141, 0}, new int[]{255, 137, 0}, new int[]{255, 134, 0}, new int[]{255, 130, 0}, new int[]{255, 126, 0}, new int[]{255, 122, 0}, new int[]{255, 119, 0}, new int[]{255, 115, 0}, new int[]{255, 111, 0}, new int[]{255, 108, 0}, new int[]{255, 104, 0}, new int[]{255, 100, 0}, new int[]{255, 96, 0}, new int[]{255, 93, 0}, new int[]{255, 89, 0}, new int[]{255, 85, 0}, new int[]{255, 82, 0}, new int[]{255, 78, 0}, new int[]{255, 74, 0}, new int[]{255, 71, 0}, new int[]{255, 67, 0}, new int[]{255, 63, 0}, new int[]{255, 59, 0}, new int[]{255, 56, 0}, new int[]{255, 52, 0}, new int[]{255, 48, 0}, new int[]{255, 45, 0}, new int[]{255, 41, 0}, new int[]{255, 37, 0}, new int[]{255, 34, 0}, new int[]{255, 30, 0}, new int[]{255, 26, 0}, new int[]{255, 22, 0}, new int[]{255, 19, 0}, new int[]{ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 15, 0}, new int[]{246, 11, 0}, new int[]{241, 8, 0}, new int[]{237, 4, 0}, new int[]{232, 0, 0}, new int[]{228, 0, 0}, new int[]{223, 0, 0}, new int[]{218, 0, 0}, new int[]{214, 0, 0}, new int[]{209, 0, 0}, new int[]{HttpStatus.SC_RESET_CONTENT, 0, 0}, new int[]{200, 0, 0}, new int[]{196, 0, 0}, new int[]{191, 0, 0}, new int[]{187, 0, 0}, new int[]{182, 0, 0}, new int[]{178, 0, 0}, new int[]{173, 0, 0}, new int[]{168, 0, 0}, new int[]{164, 0, 0}, new int[]{159, 0, 0}, new int[]{155, 0, 0}, new int[]{150, 0, 0}, new int[]{146, 0, 0}, new int[]{141, 0, 0}, new int[]{137, 0, 0}, new int[]{132, 0, 0}};

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
    /* compiled from: DisplayRGBDActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/DisplayRGBDActivity$Companion;", "", "()V", "rgbdSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "getRgbdSensor", "()Lcom/pudutech/rgbdlib/RGBDSensor;", "setRgbdSensor", "(Lcom/pudutech/rgbdlib/RGBDSensor;)V", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RGBDSensor getRgbdSensor() {
            return DisplayRGBDActivity.rgbdSensor;
        }

        public final void setRgbdSensor(RGBDSensor rGBDSensor) {
            DisplayRGBDActivity.rgbdSensor = rGBDSensor;
        }
    }

    public final CoroutineScope getTheScope() {
        return this.theScope;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(1:3)|4|(3:(2:6|(1:8)(0))|10|(1:12)(34:(1:90)(1:19)|20|(1:89)(1:26)|27|(1:88)(1:33)|34|(1:87)(1:40)|41|(1:43)(1:86)|44|45|(1:47)(1:85)|48|49|(1:51)(1:84)|52|53|(1:55)(1:83)|56|57|(1:59)|60|(1:62)|63|(1:67)|68|69|(1:71)|72|(1:74)|75|(1:79)|81|82))(0)|9|10|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0238, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0239, code lost:
    
        com.pudutech.base.Pdlog.m3274e(r6.TAG, "read rgbd config file exception " + r7.getLocalizedMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        RGBDConfig rGBDConfig;
        AppCompatButton appCompatButton;
        int i;
        AppCompatButton appCompatButton2;
        int i2;
        AppCompatButton appCompatButton3;
        int i3;
        AppCompatButton appCompatButton4;
        int i4;
        RGBDJson down_rgbd;
        Boolean shield;
        RGBDJson center_rgbd;
        Boolean shield2;
        RGBDJson right_rgbd;
        Boolean shield3;
        RGBDJson left_rgbd;
        Boolean shield4;
        super.onCreate(savedInstanceState);
        setContentView(C5657R.layout.activity_display_rgbd);
        if (rgbdSensor == null) {
            Pdlog.m3274e(this.TAG, "param error: RGBDSensor is null");
            Toast.makeText(this, "rgbd is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        int i5 = this.maxDepth;
        int i6 = this.minDepth;
        int i7 = i5 - i6;
        if (i6 <= i5) {
            while (true) {
                this.depthColorTable.add(Integer.valueOf(((i6 - this.minDepth) * 255) / i7));
                if (i6 != i5) {
                    i6++;
                }
            }
            rGBDConfig = (RGBDConfig) new Gson().fromJson((Reader) new FileReader(this.configFileName), RGBDConfig.class);
            if (rGBDConfig.getReset() != null) {
                return;
            }
            this.sheildLeftRgbd = (rGBDConfig == null || (left_rgbd = rGBDConfig.getLeft_rgbd()) == null || (shield4 = left_rgbd.getShield()) == null) ? true : shield4.booleanValue();
            this.sheildRightRgbd = (rGBDConfig == null || (right_rgbd = rGBDConfig.getRight_rgbd()) == null || (shield3 = right_rgbd.getShield()) == null) ? true : shield3.booleanValue();
            this.sheildCenterRgbd = (rGBDConfig == null || (center_rgbd = rGBDConfig.getCenter_rgbd()) == null || (shield2 = center_rgbd.getShield()) == null) ? true : shield2.booleanValue();
            this.sheildDownRgbd = (rGBDConfig == null || (down_rgbd = rGBDConfig.getDown_rgbd()) == null || (shield = down_rgbd.getShield()) == null) ? true : shield.booleanValue();
            if (this.sheildLeftRgbd) {
                appCompatButton = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_left);
                i = C5657R.string.sheild;
            } else {
                appCompatButton = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_left);
                i = C5657R.string.lButton;
            }
            appCompatButton.setText(i);
            if (this.sheildRightRgbd) {
                appCompatButton2 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_right);
                i2 = C5657R.string.sheild;
            } else {
                appCompatButton2 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_right);
                i2 = C5657R.string.rButton;
            }
            appCompatButton2.setText(i2);
            if (this.sheildCenterRgbd) {
                appCompatButton3 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_center);
                i3 = C5657R.string.sheild;
            } else {
                appCompatButton3 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_center);
                i3 = C5657R.string.cButton;
            }
            appCompatButton3.setText(i3);
            if (this.sheildDownRgbd) {
                appCompatButton4 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_down);
                i4 = C5657R.string.sheild;
            } else {
                appCompatButton4 = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_down);
                i4 = C5657R.string.dButton;
            }
            appCompatButton4.setText(i4);
            if (rGBDConfig.getLeft_rgbd() == null) {
                ImageView leftView = (ImageView) _$_findCachedViewById(C5657R.id.leftView);
                Intrinsics.checkExpressionValueIsNotNull(leftView, "leftView");
                leftView.setVisibility(8);
                AppCompatButton button_left = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_left);
                Intrinsics.checkExpressionValueIsNotNull(button_left, "button_left");
                button_left.setVisibility(8);
            }
            if (rGBDConfig.getRight_rgbd() == null) {
                ImageView rightView = (ImageView) _$_findCachedViewById(C5657R.id.rightView);
                Intrinsics.checkExpressionValueIsNotNull(rightView, "rightView");
                rightView.setVisibility(8);
                AppCompatButton button_right = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_right);
                Intrinsics.checkExpressionValueIsNotNull(button_right, "button_right");
                button_right.setVisibility(8);
            }
            if (rGBDConfig.getLeft_rgbd() == null && rGBDConfig.getRight_rgbd() == null) {
                LinearLayout leftright_Layout = (LinearLayout) _$_findCachedViewById(C5657R.id.leftright_Layout);
                Intrinsics.checkExpressionValueIsNotNull(leftright_Layout, "leftright_Layout");
                leftright_Layout.setVisibility(8);
            }
            if (rGBDConfig.getCenter_rgbd() == null) {
                ImageView centerView = (ImageView) _$_findCachedViewById(C5657R.id.centerView);
                Intrinsics.checkExpressionValueIsNotNull(centerView, "centerView");
                centerView.setVisibility(8);
                AppCompatButton button_center = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_center);
                Intrinsics.checkExpressionValueIsNotNull(button_center, "button_center");
                button_center.setVisibility(8);
                Button button_checkTool = (Button) _$_findCachedViewById(C5657R.id.button_checkTool);
                Intrinsics.checkExpressionValueIsNotNull(button_checkTool, "button_checkTool");
                button_checkTool.setVisibility(8);
                LinearLayout center_layout = (LinearLayout) _$_findCachedViewById(C5657R.id.center_layout);
                Intrinsics.checkExpressionValueIsNotNull(center_layout, "center_layout");
                center_layout.setVisibility(8);
            }
            if (rGBDConfig.getDown_rgbd() == null) {
                ImageView downView = (ImageView) _$_findCachedViewById(C5657R.id.downView);
                Intrinsics.checkExpressionValueIsNotNull(downView, "downView");
                downView.setVisibility(8);
                AppCompatButton button_down = (AppCompatButton) _$_findCachedViewById(C5657R.id.button_down);
                Intrinsics.checkExpressionValueIsNotNull(button_down, "button_down");
                button_down.setVisibility(8);
                LinearLayout down_layout = (LinearLayout) _$_findCachedViewById(C5657R.id.down_layout);
                Intrinsics.checkExpressionValueIsNotNull(down_layout, "down_layout");
                down_layout.setVisibility(8);
            }
            RGBDSensor rGBDSensor = rgbdSensor;
            if (rGBDSensor != null && rGBDSensor.getMachineModelId() == MachineModel.Firefox.getId()) {
                Button button_checkTool2 = (Button) _$_findCachedViewById(C5657R.id.button_checkTool);
                Intrinsics.checkExpressionValueIsNotNull(button_checkTool2, "button_checkTool");
                button_checkTool2.setVisibility(8);
            }
            this.viewMap.put(Integer.valueOf(C5657R.id.leftView), (ImageView) _$_findCachedViewById(C5657R.id.leftView));
            this.viewMap.put(Integer.valueOf(C5657R.id.rightView), (ImageView) _$_findCachedViewById(C5657R.id.rightView));
            this.viewMap.put(Integer.valueOf(C5657R.id.centerView), (ImageView) _$_findCachedViewById(C5657R.id.centerView));
            this.viewMap.put(Integer.valueOf(C5657R.id.downView), (ImageView) _$_findCachedViewById(C5657R.id.downView));
            this.viewIdMap.put(Integer.valueOf(C5657R.id.leftView), Integer.valueOf(C5657R.id.leftView));
            this.viewIdMap.put(Integer.valueOf(C5657R.id.rightView), Integer.valueOf(C5657R.id.rightView));
            this.viewIdMap.put(Integer.valueOf(C5657R.id.centerView), Integer.valueOf(C5657R.id.centerView));
            this.viewIdMap.put(Integer.valueOf(C5657R.id.downView), Integer.valueOf(C5657R.id.downView));
            ((Button) _$_findCachedViewById(C5657R.id.switch_img)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    boolean z;
                    String str;
                    String str2;
                    z = DisplayRGBDActivity.this.showDepth;
                    if (z) {
                        str2 = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str2, "start show ir");
                        BuildersKt__Builders_commonKt.launch$default(DisplayRGBDActivity.this.getTheScope(), null, null, new C56581(null), 3, null);
                    } else {
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show depth");
                        BuildersKt__Builders_commonKt.launch$default(DisplayRGBDActivity.this.getTheScope(), null, null, new C56592(null), 3, null);
                    }
                }

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$2$1", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$2$1 */
                /* loaded from: classes2.dex */
                static final class C56581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7135p$;

                    C56581(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56581 c56581 = new C56581(completion);
                        c56581.f7135p$ = (CoroutineScope) obj;
                        return c56581;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show ir");
                        DisplayRGBDActivity.this.startIRPreview();
                        DisplayRGBDActivity.this.showDepth = false;
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$2$2", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$2$2 */
                /* loaded from: classes2.dex */
                static final class C56592 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7136p$;

                    C56592(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56592 c56592 = new C56592(completion);
                        c56592.f7136p$ = (CoroutineScope) obj;
                        return c56592;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56592) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show depth");
                        DisplayRGBDActivity.this.startDepthPreview();
                        DisplayRGBDActivity.this.showDepth = true;
                        return Unit.INSTANCE;
                    }
                }
            });
            ((AppCompatButton) _$_findCachedViewById(C5657R.id.button_left)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$3

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$3$1", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$3$1 */
                /* loaded from: classes5.dex */
                static final class C56601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7137p$;

                    C56601(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56601 c56601 = new C56601(completion);
                        c56601.f7137p$ = (CoroutineScope) obj;
                        return c56601;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f7137p$;
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show ir");
                        DisplayRGBDActivity.this.startIRPreview();
                        DisplayRGBDActivity.this.showDepth = false;
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$3$2", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$3$2 */
                /* loaded from: classes5.dex */
                static final class C56612 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7138p$;

                    C56612(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56612 c56612 = new C56612(completion);
                        c56612.f7138p$ = (CoroutineScope) obj;
                        return c56612;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56612) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f7138p$;
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show depth");
                        DisplayRGBDActivity.this.startDepthPreview();
                        DisplayRGBDActivity.this.showDepth = true;
                        return Unit.INSTANCE;
                    }
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    boolean z;
                    boolean z2;
                    AppCompatButton appCompatButton5;
                    int i8;
                    DisplayRGBDActivity displayRGBDActivity = DisplayRGBDActivity.this;
                    z = displayRGBDActivity.sheildLeftRgbd;
                    displayRGBDActivity.sheildLeftRgbd = !z;
                    z2 = DisplayRGBDActivity.this.sheildLeftRgbd;
                    if (z2) {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_left);
                        i8 = C5657R.string.sheild;
                    } else {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_left);
                        i8 = C5657R.string.lButton;
                    }
                    appCompatButton5.setText(i8);
                }
            });
            ((AppCompatButton) _$_findCachedViewById(C5657R.id.button_right)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    boolean z;
                    boolean z2;
                    AppCompatButton appCompatButton5;
                    int i8;
                    DisplayRGBDActivity displayRGBDActivity = DisplayRGBDActivity.this;
                    z = displayRGBDActivity.sheildRightRgbd;
                    displayRGBDActivity.sheildRightRgbd = !z;
                    z2 = DisplayRGBDActivity.this.sheildRightRgbd;
                    if (z2) {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_right);
                        i8 = C5657R.string.sheild;
                    } else {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_right);
                        i8 = C5657R.string.rButton;
                    }
                    appCompatButton5.setText(i8);
                }
            });
            ((AppCompatButton) _$_findCachedViewById(C5657R.id.button_center)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    boolean z;
                    boolean z2;
                    AppCompatButton appCompatButton5;
                    int i8;
                    DisplayRGBDActivity displayRGBDActivity = DisplayRGBDActivity.this;
                    z = displayRGBDActivity.sheildCenterRgbd;
                    displayRGBDActivity.sheildCenterRgbd = !z;
                    z2 = DisplayRGBDActivity.this.sheildCenterRgbd;
                    if (z2) {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_center);
                        i8 = C5657R.string.sheild;
                    } else {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_center);
                        i8 = C5657R.string.cButton;
                    }
                    appCompatButton5.setText(i8);
                }

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$5$1", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$5$1 */
                /* loaded from: classes6.dex */
                static final class C56621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7139p$;

                    C56621(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56621 c56621 = new C56621(completion);
                        c56621.f7139p$ = (CoroutineScope) obj;
                        return c56621;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f7139p$;
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show ir");
                        DisplayRGBDActivity.this.startIRPreview();
                        DisplayRGBDActivity.this.showDepth = false;
                        return Unit.INSTANCE;
                    }
                }

                /* compiled from: DisplayRGBDActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$5$2", m3970f = "DisplayRGBDActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$5$2 */
                /* loaded from: classes6.dex */
                static final class C56632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f7140p$;

                    C56632(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C56632 c56632 = new C56632(completion);
                        c56632.f7140p$ = (CoroutineScope) obj;
                        return c56632;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C56632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f7140p$;
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3273d(str, "start show depth");
                        DisplayRGBDActivity.this.startDepthPreview();
                        DisplayRGBDActivity.this.showDepth = true;
                        return Unit.INSTANCE;
                    }
                }
            });
            ((AppCompatButton) _$_findCachedViewById(C5657R.id.button_down)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    boolean z;
                    boolean z2;
                    AppCompatButton appCompatButton5;
                    int i8;
                    DisplayRGBDActivity displayRGBDActivity = DisplayRGBDActivity.this;
                    z = displayRGBDActivity.sheildDownRgbd;
                    displayRGBDActivity.sheildDownRgbd = !z;
                    z2 = DisplayRGBDActivity.this.sheildDownRgbd;
                    if (z2) {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_down);
                        i8 = C5657R.string.sheild;
                    } else {
                        appCompatButton5 = (AppCompatButton) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_down);
                        i8 = C5657R.string.dButton;
                    }
                    appCompatButton5.setText(i8);
                }
            });
            ((Button) _$_findCachedViewById(C5657R.id.button_save)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    String str2;
                    HashMap hashMap;
                    HashMap hashMap2;
                    HashMap hashMap3;
                    HashMap hashMap4;
                    HashMap hashMap5;
                    HashMap hashMap6;
                    HashMap hashMap7;
                    HashMap hashMap8;
                    String str3;
                    HashMap hashMap9;
                    HashMap hashMap10;
                    HashMap hashMap11;
                    HashMap hashMap12;
                    HashMap hashMap13;
                    HashMap hashMap14;
                    HashMap hashMap15;
                    HashMap hashMap16;
                    String type;
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
                        str2 = DisplayRGBDActivity.this.configFileName;
                        RGBDConfig rGBDConfig2 = (RGBDConfig) gson.fromJson((Reader) new FileReader(str2), RGBDConfig.class);
                        hashMap = DisplayRGBDActivity.this.saveMap;
                        HashMap hashMap33 = hashMap;
                        Integer valueOf = Integer.valueOf(C5657R.id.leftView);
                        RGBDJson left_rgbd2 = rGBDConfig2.getLeft_rgbd();
                        hashMap33.put(valueOf, left_rgbd2 != null ? left_rgbd2.getSerial() : null);
                        hashMap2 = DisplayRGBDActivity.this.saveMap;
                        HashMap hashMap34 = hashMap2;
                        Integer valueOf2 = Integer.valueOf(C5657R.id.rightView);
                        RGBDJson right_rgbd2 = rGBDConfig2.getRight_rgbd();
                        hashMap34.put(valueOf2, right_rgbd2 != null ? right_rgbd2.getSerial() : null);
                        hashMap3 = DisplayRGBDActivity.this.saveMap;
                        HashMap hashMap35 = hashMap3;
                        Integer valueOf3 = Integer.valueOf(C5657R.id.centerView);
                        RGBDJson center_rgbd2 = rGBDConfig2.getCenter_rgbd();
                        hashMap35.put(valueOf3, center_rgbd2 != null ? center_rgbd2.getSerial() : null);
                        hashMap4 = DisplayRGBDActivity.this.saveMap;
                        HashMap hashMap36 = hashMap4;
                        Integer valueOf4 = Integer.valueOf(C5657R.id.downView);
                        RGBDJson down_rgbd2 = rGBDConfig2.getDown_rgbd();
                        hashMap36.put(valueOf4, down_rgbd2 != null ? down_rgbd2.getSerial() : null);
                        hashMap5 = DisplayRGBDActivity.this.saveTypeMap;
                        HashMap hashMap37 = hashMap5;
                        Integer valueOf5 = Integer.valueOf(C5657R.id.leftView);
                        RGBDJson left_rgbd3 = rGBDConfig2.getLeft_rgbd();
                        hashMap37.put(valueOf5, left_rgbd3 != null ? left_rgbd3.getType() : null);
                        hashMap6 = DisplayRGBDActivity.this.saveTypeMap;
                        HashMap hashMap38 = hashMap6;
                        Integer valueOf6 = Integer.valueOf(C5657R.id.rightView);
                        RGBDJson right_rgbd3 = rGBDConfig2.getRight_rgbd();
                        hashMap38.put(valueOf6, right_rgbd3 != null ? right_rgbd3.getType() : null);
                        hashMap7 = DisplayRGBDActivity.this.saveTypeMap;
                        HashMap hashMap39 = hashMap7;
                        Integer valueOf7 = Integer.valueOf(C5657R.id.centerView);
                        RGBDJson center_rgbd3 = rGBDConfig2.getCenter_rgbd();
                        hashMap39.put(valueOf7, center_rgbd3 != null ? center_rgbd3.getType() : null);
                        hashMap8 = DisplayRGBDActivity.this.saveTypeMap;
                        HashMap hashMap40 = hashMap8;
                        Integer valueOf8 = Integer.valueOf(C5657R.id.downView);
                        RGBDJson down_rgbd3 = rGBDConfig2.getDown_rgbd();
                        hashMap40.put(valueOf8, down_rgbd3 != null ? down_rgbd3.getType() : null);
                        RGBDJson left_rgbd4 = rGBDConfig2.getLeft_rgbd();
                        if (left_rgbd4 != null) {
                            hashMap31 = DisplayRGBDActivity.this.saveMap;
                            hashMap32 = DisplayRGBDActivity.this.viewIdMap;
                            String str4 = (String) hashMap31.get(hashMap32.get(Integer.valueOf(C5657R.id.leftView)));
                            if (str4 == null) {
                                str4 = "";
                            }
                            left_rgbd4.setSerial(str4);
                        }
                        RGBDJson right_rgbd4 = rGBDConfig2.getRight_rgbd();
                        if (right_rgbd4 != null) {
                            hashMap29 = DisplayRGBDActivity.this.saveMap;
                            hashMap30 = DisplayRGBDActivity.this.viewIdMap;
                            String str5 = (String) hashMap29.get(hashMap30.get(Integer.valueOf(C5657R.id.rightView)));
                            if (str5 == null) {
                                str5 = "";
                            }
                            right_rgbd4.setSerial(str5);
                        }
                        RGBDJson center_rgbd4 = rGBDConfig2.getCenter_rgbd();
                        if (center_rgbd4 != null) {
                            hashMap27 = DisplayRGBDActivity.this.saveMap;
                            hashMap28 = DisplayRGBDActivity.this.viewIdMap;
                            String str6 = (String) hashMap27.get(hashMap28.get(Integer.valueOf(C5657R.id.centerView)));
                            if (str6 == null) {
                                str6 = "";
                            }
                            center_rgbd4.setSerial(str6);
                        }
                        RGBDJson down_rgbd4 = rGBDConfig2.getDown_rgbd();
                        if (down_rgbd4 != null) {
                            hashMap25 = DisplayRGBDActivity.this.saveMap;
                            hashMap26 = DisplayRGBDActivity.this.viewIdMap;
                            String str7 = (String) hashMap25.get(hashMap26.get(Integer.valueOf(C5657R.id.downView)));
                            if (str7 == null) {
                                str7 = "";
                            }
                            down_rgbd4.setSerial(str7);
                        }
                        RGBDJson left_rgbd5 = rGBDConfig2.getLeft_rgbd();
                        if (left_rgbd5 != null) {
                            hashMap23 = DisplayRGBDActivity.this.saveTypeMap;
                            hashMap24 = DisplayRGBDActivity.this.viewIdMap;
                            String str8 = (String) hashMap23.get(hashMap24.get(Integer.valueOf(C5657R.id.leftView)));
                            if (str8 == null) {
                                str8 = "";
                            }
                            left_rgbd5.setType(str8);
                        }
                        RGBDJson right_rgbd5 = rGBDConfig2.getRight_rgbd();
                        if (right_rgbd5 != null) {
                            hashMap21 = DisplayRGBDActivity.this.saveTypeMap;
                            hashMap22 = DisplayRGBDActivity.this.viewIdMap;
                            String str9 = (String) hashMap21.get(hashMap22.get(Integer.valueOf(C5657R.id.rightView)));
                            if (str9 == null) {
                                str9 = "";
                            }
                            right_rgbd5.setType(str9);
                        }
                        RGBDJson center_rgbd5 = rGBDConfig2.getCenter_rgbd();
                        if (center_rgbd5 != null) {
                            hashMap19 = DisplayRGBDActivity.this.saveTypeMap;
                            hashMap20 = DisplayRGBDActivity.this.viewIdMap;
                            String str10 = (String) hashMap19.get(hashMap20.get(Integer.valueOf(C5657R.id.centerView)));
                            if (str10 == null) {
                                str10 = "";
                            }
                            center_rgbd5.setType(str10);
                        }
                        RGBDJson down_rgbd5 = rGBDConfig2.getDown_rgbd();
                        if (down_rgbd5 != null) {
                            hashMap17 = DisplayRGBDActivity.this.saveTypeMap;
                            hashMap18 = DisplayRGBDActivity.this.viewIdMap;
                            String str11 = (String) hashMap17.get(hashMap18.get(Integer.valueOf(C5657R.id.downView)));
                            down_rgbd5.setType(str11 != null ? str11 : "");
                        }
                        rGBDConfig2.setReset(false);
                        RGBDJson left_rgbd6 = rGBDConfig2.getLeft_rgbd();
                        if (left_rgbd6 != null) {
                            z4 = DisplayRGBDActivity.this.sheildLeftRgbd;
                            left_rgbd6.setShield(Boolean.valueOf(z4));
                        }
                        RGBDJson right_rgbd6 = rGBDConfig2.getRight_rgbd();
                        if (right_rgbd6 != null) {
                            z3 = DisplayRGBDActivity.this.sheildRightRgbd;
                            right_rgbd6.setShield(Boolean.valueOf(z3));
                        }
                        RGBDJson center_rgbd6 = rGBDConfig2.getCenter_rgbd();
                        if (center_rgbd6 != null) {
                            z2 = DisplayRGBDActivity.this.sheildCenterRgbd;
                            center_rgbd6.setShield(Boolean.valueOf(z2));
                        }
                        RGBDJson down_rgbd6 = rGBDConfig2.getDown_rgbd();
                        if (down_rgbd6 != null) {
                            z = DisplayRGBDActivity.this.sheildDownRgbd;
                            down_rgbd6.setShield(Boolean.valueOf(z));
                        }
                        RGBDJson center_rgbd7 = rGBDConfig2.getCenter_rgbd();
                        if (center_rgbd7 != null && (type = center_rgbd7.getType()) != null) {
                            if (Intrinsics.areEqual(type, RGBDType.RealSense.getStr())) {
                                RGBDSensor rgbdSensor2 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                                if (rgbdSensor2 != null) {
                                    rgbdSensor2.setCenterRgbdType(1);
                                }
                            } else if (Intrinsics.areEqual(type, RGBDType.Orbbec.getStr())) {
                                RGBDSensor rgbdSensor3 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                                if (rgbdSensor3 != null) {
                                    rgbdSensor3.setCenterRgbdType(0);
                                }
                            } else if (Intrinsics.areEqual(type, RGBDType.AngStrong.getStr())) {
                                RGBDSensor rgbdSensor4 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                                if (rgbdSensor4 != null) {
                                    rgbdSensor4.setCenterRgbdType(3);
                                }
                            } else {
                                RGBDSensor rgbdSensor5 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                                if (rgbdSensor5 != null) {
                                    rgbdSensor5.setCenterRgbdType(-1);
                                }
                            }
                        }
                        str3 = DisplayRGBDActivity.this.configFileName;
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(str3));
                        String json = gson.toJson(rGBDConfig2);
                        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(configure)");
                        Charset charset = Charsets.UTF_8;
                        if (json == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes = json.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        fileOutputStream.write(bytes);
                        fileOutputStream.close();
                        Toast.makeText(DisplayRGBDActivity.this, "保存配置成功", 0).show();
                        DisplayRGBDActivity.this.stopPreview();
                        RGBDSensor rgbdSensor6 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                        if (rgbdSensor6 != null) {
                            rgbdSensor6.RefreshConfigure();
                        }
                        hashMap9 = DisplayRGBDActivity.this.viewMap;
                        hashMap9.put(Integer.valueOf(C5657R.id.leftView), (ImageView) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.leftView));
                        hashMap10 = DisplayRGBDActivity.this.viewMap;
                        hashMap10.put(Integer.valueOf(C5657R.id.rightView), (ImageView) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.rightView));
                        hashMap11 = DisplayRGBDActivity.this.viewMap;
                        hashMap11.put(Integer.valueOf(C5657R.id.centerView), (ImageView) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.centerView));
                        hashMap12 = DisplayRGBDActivity.this.viewMap;
                        hashMap12.put(Integer.valueOf(C5657R.id.downView), (ImageView) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.downView));
                        hashMap13 = DisplayRGBDActivity.this.viewIdMap;
                        hashMap13.put(Integer.valueOf(C5657R.id.leftView), Integer.valueOf(C5657R.id.leftView));
                        hashMap14 = DisplayRGBDActivity.this.viewIdMap;
                        hashMap14.put(Integer.valueOf(C5657R.id.rightView), Integer.valueOf(C5657R.id.rightView));
                        hashMap15 = DisplayRGBDActivity.this.viewIdMap;
                        hashMap15.put(Integer.valueOf(C5657R.id.centerView), Integer.valueOf(C5657R.id.centerView));
                        hashMap16 = DisplayRGBDActivity.this.viewIdMap;
                        hashMap16.put(Integer.valueOf(C5657R.id.downView), Integer.valueOf(C5657R.id.downView));
                        DisplayRGBDActivity.this.clickImage = -1;
                        DisplayRGBDActivity.this.startDepthPreview();
                        RGBDSensor rgbdSensor7 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                        if (rgbdSensor7 == null || rgbdSensor7.checkDownRgbdConfigFile()) {
                            return;
                        }
                        ((Button) DisplayRGBDActivity.this._$_findCachedViewById(C5657R.id.button_checkTool)).callOnClick();
                    } catch (Exception e) {
                        str = DisplayRGBDActivity.this.TAG;
                        Pdlog.m3274e(str, "read rgbd config file exception " + e.getLocalizedMessage());
                    }
                }
            });
            ((Button) _$_findCachedViewById(C5657R.id.button_checkTool)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.rgbdlib.activity.DisplayRGBDActivity$onCreate$8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RGBDSensor rgbdSensor2 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                    if (rgbdSensor2 != null) {
                        DisplayRGBDActivity displayRGBDActivity = DisplayRGBDActivity.this;
                        RGBDSensor rgbdSensor3 = DisplayRGBDActivity.INSTANCE.getRgbdSensor();
                        if (rgbdSensor3 == null) {
                            Intrinsics.throwNpe();
                        }
                        rgbdSensor2.startCheckToolActivity(displayRGBDActivity, rgbdSensor3);
                    }
                }
            });
            DisplayRGBDActivity displayRGBDActivity = this;
            ((ImageView) _$_findCachedViewById(C5657R.id.leftView)).setOnClickListener(displayRGBDActivity);
            ((ImageView) _$_findCachedViewById(C5657R.id.rightView)).setOnClickListener(displayRGBDActivity);
            ((ImageView) _$_findCachedViewById(C5657R.id.centerView)).setOnClickListener(displayRGBDActivity);
            ((ImageView) _$_findCachedViewById(C5657R.id.downView)).setOnClickListener(displayRGBDActivity);
            return;
        }
        rGBDConfig = (RGBDConfig) new Gson().fromJson((Reader) new FileReader(this.configFileName), RGBDConfig.class);
        if (rGBDConfig.getReset() != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        BuildersKt__Builders_commonKt.launch$default(this.theScope, null, null, new DisplayRGBDActivity$onPause$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BuildersKt__Builders_commonKt.launch$default(this.theScope, null, null, new DisplayRGBDActivity$onDestroy$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BuildersKt__Builders_commonKt.launch$default(this.theScope, null, null, new DisplayRGBDActivity$onResume$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLeftImage(Bitmap bitmap) {
        ImageView imageView = this.viewMap.get(Integer.valueOf(C5657R.id.leftView));
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        ImageView imageView2 = this.viewMap.get(Integer.valueOf(C5657R.id.leftView));
        if (imageView2 != null) {
            imageView2.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRightImage(Bitmap bitmap) {
        ImageView imageView = this.viewMap.get(Integer.valueOf(C5657R.id.rightView));
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        ImageView imageView2 = this.viewMap.get(Integer.valueOf(C5657R.id.rightView));
        if (imageView2 != null) {
            imageView2.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCenterImage(Bitmap bitmap) {
        ImageView imageView = this.viewMap.get(Integer.valueOf(C5657R.id.centerView));
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        ImageView imageView2 = this.viewMap.get(Integer.valueOf(C5657R.id.centerView));
        if (imageView2 != null) {
            imageView2.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDownImage(Bitmap bitmap) {
        ImageView imageView = this.viewMap.get(Integer.valueOf(C5657R.id.downView));
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        ImageView imageView2 = this.viewMap.get(Integer.valueOf(C5657R.id.downView));
        if (imageView2 != null) {
            imageView2.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPreview() {
        ThreadSafeListener<RGBDDataCatcher> downRGBDListener;
        ThreadSafeListener<RGBDDataCatcher> downRGBDListener2;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners2;
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners2;
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners2;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor != null && (leftRGBDListeners2 = rGBDSensor.getLeftRGBDListeners()) != null) {
            leftRGBDListeners2.remove("preview_ir");
        }
        RGBDSensor rGBDSensor2 = rgbdSensor;
        if (rGBDSensor2 != null && (leftRGBDListeners = rGBDSensor2.getLeftRGBDListeners()) != null) {
            leftRGBDListeners.remove("preview_dp");
        }
        RGBDSensor rGBDSensor3 = rgbdSensor;
        if (rGBDSensor3 != null && (rightRGBDListeners2 = rGBDSensor3.getRightRGBDListeners()) != null) {
            rightRGBDListeners2.remove("preview_ir");
        }
        RGBDSensor rGBDSensor4 = rgbdSensor;
        if (rGBDSensor4 != null && (rightRGBDListeners = rGBDSensor4.getRightRGBDListeners()) != null) {
            rightRGBDListeners.remove("preview_dp");
        }
        RGBDSensor rGBDSensor5 = rgbdSensor;
        if (rGBDSensor5 != null && (centernRGBDListeners2 = rGBDSensor5.getCenternRGBDListeners()) != null) {
            centernRGBDListeners2.remove("preview_ir");
        }
        RGBDSensor rGBDSensor6 = rgbdSensor;
        if (rGBDSensor6 != null && (centernRGBDListeners = rGBDSensor6.getCenternRGBDListeners()) != null) {
            centernRGBDListeners.remove("preview_dp");
        }
        RGBDSensor rGBDSensor7 = rgbdSensor;
        if (rGBDSensor7 != null && (downRGBDListener2 = rGBDSensor7.getDownRGBDListener()) != null) {
            downRGBDListener2.remove("preview_ir");
        }
        RGBDSensor rGBDSensor8 = rgbdSensor;
        if (rGBDSensor8 == null || (downRGBDListener = rGBDSensor8.getDownRGBDListener()) == null) {
            return;
        }
        downRGBDListener.remove("preview_dp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startIRPreview() {
        stopPreview();
        startLeftIRPreview();
        startRightIRPreview();
        startCenterIRPreview();
        startDownIRPreview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDepthPreview() {
        stopPreview();
        startLeftDepthPreview();
        startRightDepthPreview();
        startCenterDepthPreview();
        startDownDepthPreview();
    }

    private final void startLeftIRPreview() {
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (leftRGBDListeners = rGBDSensor.getLeftRGBDListeners()) == null) {
            return;
        }
        leftRGBDListeners.add("preview_ir", new DisplayRGBDActivity$startLeftIRPreview$1(this));
    }

    private final void startLeftDepthPreview() {
        ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (leftRGBDListeners = rGBDSensor.getLeftRGBDListeners()) == null) {
            return;
        }
        leftRGBDListeners.add("preview_dp", new DisplayRGBDActivity$startLeftDepthPreview$1(this));
    }

    private final void startRightIRPreview() {
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (rightRGBDListeners = rGBDSensor.getRightRGBDListeners()) == null) {
            return;
        }
        rightRGBDListeners.add("preview_ir", new DisplayRGBDActivity$startRightIRPreview$1(this));
    }

    private final void startRightDepthPreview() {
        ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (rightRGBDListeners = rGBDSensor.getRightRGBDListeners()) == null) {
            return;
        }
        rightRGBDListeners.add("preview_dp", new DisplayRGBDActivity$startRightDepthPreview$1(this));
    }

    private final void startCenterIRPreview() {
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (centernRGBDListeners = rGBDSensor.getCenternRGBDListeners()) == null) {
            return;
        }
        centernRGBDListeners.add("preview_ir", new DisplayRGBDActivity$startCenterIRPreview$1(this));
    }

    private final void startCenterDepthPreview() {
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (centernRGBDListeners = rGBDSensor.getCenternRGBDListeners()) == null) {
            return;
        }
        centernRGBDListeners.add("preview_dp", new DisplayRGBDActivity$startCenterDepthPreview$1(this));
    }

    private final void startDownIRPreview() {
        ThreadSafeListener<RGBDDataCatcher> downRGBDListener;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (downRGBDListener = rGBDSensor.getDownRGBDListener()) == null) {
            return;
        }
        downRGBDListener.add("preview_ir", new DisplayRGBDActivity$startDownIRPreview$1(this));
    }

    private final void startDownDepthPreview() {
        ThreadSafeListener<RGBDDataCatcher> downRGBDListener;
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor == null || (downRGBDListener = rGBDSensor.getDownRGBDListener()) == null) {
            return;
        }
        downRGBDListener.add("preview_dp", new DisplayRGBDActivity$startDownDepthPreview$1(this));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        int i = this.clickImage;
        if (i == -1 || i == p0.getId()) {
            this.clickImage = p0.getId();
            return;
        }
        Integer num = this.viewIdMap.get(Integer.valueOf(p0.getId()));
        this.viewIdMap.put(Integer.valueOf(p0.getId()), this.viewIdMap.get(Integer.valueOf(this.clickImage)));
        this.viewIdMap.put(Integer.valueOf(this.clickImage), num);
        ImageView imageView = this.viewMap.get(this.viewIdMap.get(Integer.valueOf(p0.getId())));
        this.viewMap.put(this.viewIdMap.get(Integer.valueOf(p0.getId())), this.viewMap.get(this.viewIdMap.get(Integer.valueOf(this.clickImage))));
        this.viewMap.put(this.viewIdMap.get(Integer.valueOf(this.clickImage)), imageView);
        ImageView imageView2 = this.viewMap.get(this.viewIdMap.get(Integer.valueOf(p0.getId())));
        if (imageView2 != null) {
            imageView2.setImageDrawable(null);
        }
        ImageView imageView3 = this.viewMap.get(this.viewIdMap.get(Integer.valueOf(this.clickImage)));
        if (imageView3 != null) {
            imageView3.setImageDrawable(null);
        }
        this.clickImage = -1;
    }
}
