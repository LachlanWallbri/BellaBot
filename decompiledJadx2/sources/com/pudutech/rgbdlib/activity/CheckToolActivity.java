package com.pudutech.rgbdlib.activity;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import com.pudutech.rgbdlib.RGBDSensor;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/CheckToolActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "depthColorMap", "", "", "[[I", "depthColorTable", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "maxDepth", "minDepth", SpeechUtility.TAG_RESOURCE_RESULT, "", "Ljava/lang/Boolean;", "retSaveImage", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CheckToolActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RGBDSensor rgbdSensor;
    private HashMap _$_findViewCache;
    private final String TAG = "RGBDCheckTool";
    private final int minDepth = 100;
    private final int maxDepth = InternalConstant.RATE8K;
    private final ArrayList<Integer> depthColorTable = new ArrayList<>();
    private Boolean result = false;
    private Boolean retSaveImage = false;
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
    /* compiled from: CheckToolActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rgbdlib/activity/CheckToolActivity$Companion;", "", "()V", "rgbdSensor", "Lcom/pudutech/rgbdlib/RGBDSensor;", "getRgbdSensor", "()Lcom/pudutech/rgbdlib/RGBDSensor;", "setRgbdSensor", "(Lcom/pudutech/rgbdlib/RGBDSensor;)V", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RGBDSensor getRgbdSensor() {
            return CheckToolActivity.rgbdSensor;
        }

        public final void setRgbdSensor(RGBDSensor rGBDSensor) {
            CheckToolActivity.rgbdSensor = rGBDSensor;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        ThreadSafeListener<RGBDDataCatcher> checkRGBDListener;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        super.onCreate(savedInstanceState);
        setContentView(C5657R.layout.activity_check_tool);
        if (rgbdSensor == null) {
            Pdlog.m3274e(this.TAG, "param error: RGBDSensor is null");
            Toast.makeText(this, "rgbd is null. exist and try again", 1).show();
            Unit unit = Unit.INSTANCE;
        }
        int i = this.maxDepth;
        int i2 = this.minDepth;
        int i3 = i - i2;
        if (i2 <= i) {
            while (true) {
                this.depthColorTable.add(Integer.valueOf(((i2 - this.minDepth) * 255) / i3));
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        RGBDSensor rGBDSensor = rgbdSensor;
        if (rGBDSensor != null && (centernRGBDListeners = rGBDSensor.getCenternRGBDListeners()) != null) {
            centernRGBDListeners.add("rgbdTool", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.CheckToolActivity$onCreate$2
                @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                public void onFrameDescriptor(ParcelFileDescriptor p0, int p1, int p2, int p3) {
                    Boolean bool;
                    Boolean bool2;
                    Intrinsics.checkParameterIsNotNull(p0, "p0");
                    bool = CheckToolActivity.this.result;
                    if (Intrinsics.areEqual((Object) bool, (Object) false)) {
                        bool2 = CheckToolActivity.this.retSaveImage;
                        if (Intrinsics.areEqual((Object) bool2, (Object) true)) {
                            CheckToolActivity checkToolActivity = CheckToolActivity.this;
                            RGBDSensor rgbdSensor2 = CheckToolActivity.INSTANCE.getRgbdSensor();
                            checkToolActivity.result = rgbdSensor2 != null ? Boolean.valueOf(rgbdSensor2.runRGBDCheckToolSaveImg(p0.getFd(), p1, p2, p3)) : null;
                            return;
                        } else {
                            CheckToolActivity checkToolActivity2 = CheckToolActivity.this;
                            RGBDSensor rgbdSensor3 = CheckToolActivity.INSTANCE.getRgbdSensor();
                            checkToolActivity2.result = rgbdSensor3 != null ? Boolean.valueOf(rgbdSensor3.runRGBDCheckTool(p0.getFd(), p1, p2, p3)) : null;
                            return;
                        }
                    }
                    CheckToolActivity.this.finish();
                }
            });
        }
        RGBDSensor rGBDSensor2 = rgbdSensor;
        if (rGBDSensor2 != null && (checkRGBDListener = rGBDSensor2.getCheckRGBDListener()) != null) {
            checkRGBDListener.add("rgbdToolResult", new CheckToolActivity$onCreate$3(this));
        }
        ((Button) _$_findCachedViewById(C5657R.id.saveImg_button)).setOnClickListener(new CheckToolActivity$onCreate$4(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CheckToolActivity$onDestroy$1(null), 3, null);
    }
}
