package com.pudutech.mirsdk.hardware.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.library.C5193R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: RGBDPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0013H\u0002J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/RGBDPreviewActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "depthColorMap", "", "", "[[I", "depthColorTable", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "maxDepth", "minDepth", "showDepth", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "startCenterDepthPreview", "startCenterIRPreview", "startDepthPreview", "startIRPreview", "startLeftDepthPreview", "startLeftIRPreview", "startRightDepthPreview", "startRightIRPreview", "stopPreview", "updateCenterImage", "bitmap", "Landroid/graphics/Bitmap;", "updateLeftImage", "updateRightImage", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RGBDPreviewActivity extends Activity {
    private HashMap _$_findViewCache;
    private final String TAG = RGBDPreviewActivity.class.getName();
    private final int minDepth = 100;
    private final int maxDepth = InternalConstant.RATE8K;
    private final ArrayList<Integer> depthColorTable = new ArrayList<>();
    private boolean showDepth = true;
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

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5193R.layout.hardware_activity_rgbd_preview);
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
        startDepthPreview();
        ((Button) _$_findCachedViewById(C5193R.id.switch_img)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                String str;
                String str2;
                z = RGBDPreviewActivity.this.showDepth;
                if (z) {
                    str2 = RGBDPreviewActivity.this.TAG;
                    Pdlog.m3273d(str2, "start show  ir");
                    BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C51301(null), 3, null);
                } else {
                    str = RGBDPreviewActivity.this.TAG;
                    Pdlog.m3273d(str, "start show depth");
                    BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C51312(null), 3, null);
                }
            }

            /* compiled from: RGBDPreviewActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$onCreate$1$1", m3970f = "RGBDPreviewActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$onCreate$1$1 */
            /* loaded from: classes.dex */
            static final class C51301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6002p$;

                C51301(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C51301 c51301 = new C51301(completion);
                    c51301.f6002p$ = (CoroutineScope) obj;
                    return c51301;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C51301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    str = RGBDPreviewActivity.this.TAG;
                    Pdlog.m3273d(str, "start show ir");
                    RGBDPreviewActivity.this.startIRPreview();
                    RGBDPreviewActivity.this.showDepth = false;
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: RGBDPreviewActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$onCreate$1$2", m3970f = "RGBDPreviewActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$onCreate$1$2 */
            /* loaded from: classes.dex */
            static final class C51312 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6003p$;

                C51312(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C51312 c51312 = new C51312(completion);
                    c51312.f6003p$ = (CoroutineScope) obj;
                    return c51312;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C51312) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String str;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    str = RGBDPreviewActivity.this.TAG;
                    Pdlog.m3273d(str, "start show depth");
                    RGBDPreviewActivity.this.startDepthPreview();
                    RGBDPreviewActivity.this.showDepth = true;
                    return Unit.INSTANCE;
                }
            }
        });
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new RGBDPreviewActivity$onPause$1(this, null), 3, null);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new RGBDPreviewActivity$onDestroy$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLeftImage(Bitmap bitmap) {
        ((ImageView) _$_findCachedViewById(C5193R.id.leftView)).setImageBitmap(bitmap);
        ((ImageView) _$_findCachedViewById(C5193R.id.leftView)).postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRightImage(Bitmap bitmap) {
        ((ImageView) _$_findCachedViewById(C5193R.id.rightView)).setImageBitmap(bitmap);
        ((ImageView) _$_findCachedViewById(C5193R.id.rightView)).postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCenterImage(Bitmap bitmap) {
        ((ImageView) _$_findCachedViewById(C5193R.id.centerView)).setImageBitmap(bitmap);
        ((ImageView) _$_findCachedViewById(C5193R.id.centerView)).postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPreview() {
        RGBDInterface rGBDInterface;
        RGBDInterface rGBDInterface2;
        RGBDInterface rGBDInterface3;
        RGBDInterface rGBDInterface4;
        RGBDInterface rGBDInterface5;
        RGBDInterface rGBDInterface6;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface != null && (rGBDInterface6 = hardwareInterface.getRGBDInterface()) != null) {
            rGBDInterface6.removeLeftRgbdListener("preview_ir");
        }
        HardwareInterface hardwareInterface2 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface2 != null && (rGBDInterface5 = hardwareInterface2.getRGBDInterface()) != null) {
            rGBDInterface5.removeLeftRgbdListener("preview_dp");
        }
        HardwareInterface hardwareInterface3 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface3 != null && (rGBDInterface4 = hardwareInterface3.getRGBDInterface()) != null) {
            rGBDInterface4.removeRightRgbdListener("preview_ir");
        }
        HardwareInterface hardwareInterface4 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface4 != null && (rGBDInterface3 = hardwareInterface4.getRGBDInterface()) != null) {
            rGBDInterface3.removeRightRgbdListener("preview_dp");
        }
        HardwareInterface hardwareInterface5 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface5 != null && (rGBDInterface2 = hardwareInterface5.getRGBDInterface()) != null) {
            rGBDInterface2.removeCenterRgbdListener("preview_ir");
        }
        HardwareInterface hardwareInterface6 = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface6 == null || (rGBDInterface = hardwareInterface6.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.removeCenterRgbdListener("preview_dp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startIRPreview() {
        stopPreview();
        startLeftIRPreview();
        startRightIRPreview();
        startCenterIRPreview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDepthPreview() {
        stopPreview();
        startLeftDepthPreview();
        startRightDepthPreview();
        startCenterDepthPreview();
    }

    private final void startLeftIRPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addLeftRgbdListener("preview_ir", new RGBDPreviewActivity$startLeftIRPreview$1(this));
    }

    private final void startLeftDepthPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addLeftRgbdListener("preview_dp", new RGBDPreviewActivity$startLeftDepthPreview$1(this));
    }

    private final void startRightIRPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addRightRgbdListener("preview_ir", new RGBDPreviewActivity$startRightIRPreview$1(this));
    }

    private final void startRightDepthPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addRightRgbdListener("preview_dp", new RGBDPreviewActivity$startRightDepthPreview$1(this));
    }

    private final void startCenterIRPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addCenterRgbdListener("preview_ir", new RGBDPreviewActivity$startCenterIRPreview$1(this));
    }

    private final void startCenterDepthPreview() {
        RGBDInterface rGBDInterface;
        HardwareInterface hardwareInterface = HardwareConnection.INSTANCE.getInterface();
        if (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addCenterRgbdListener("preview_dp", new RGBDPreviewActivity$startCenterDepthPreview$1(this));
    }
}
