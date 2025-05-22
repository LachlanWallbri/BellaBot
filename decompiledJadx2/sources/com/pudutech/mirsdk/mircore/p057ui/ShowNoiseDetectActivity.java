package com.pudutech.mirsdk.mircore.p057ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.mirperception.Costmap;
import com.pudutech.mirsdk.mircore.p057ui.ShowNoiseDetectActivity;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ShowNoiseDetectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002+,B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0012\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001eH\u0014J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0004J\u0006\u0010)\u001a\u00020\u001eJ\u0006\u0010*\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "area", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "checkAreaHeight", "", "checkAreaWidth", "getResultJob", "Lkotlinx/coroutines/Job;", "heightUpper", "mCheckHeigth", "Landroid/widget/EditText;", "mCheckWidth", "mSwitch", "Landroid/widget/Switch;", "mediaPlayer", "Landroid/media/MediaPlayer;", "obsToShow", "Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity$DrawNoiseType;", "openVoice", "", "scale", "", "widthUpper", "drawAxis", "", "drawCheckArea", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRadioButtonClicked", "v", "Landroid/view/View;", "playMusic", "name", "setListener", "stopPlayer", "DrawNoiseType", "ObsType", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ShowNoiseDetectActivity extends Activity {
    private HashMap _$_findViewCache;
    private Job getResultJob;
    private EditText mCheckHeigth;
    private EditText mCheckWidth;
    private Switch mSwitch;
    private MediaPlayer mediaPlayer;
    private DrawNoiseType obsToShow;
    private boolean openVoice;
    private final String TAG = "ShowNoiseDetectActivity";
    private final double widthUpper = 2.0d;
    private final double heightUpper = 4.0d;
    private double checkAreaWidth = 0.8d;
    private double checkAreaHeight = 2.0d;
    private final float scale = 5.0f;
    private Vector3d[] area = {new Vector3d(this.checkAreaHeight, this.checkAreaWidth / 2.0d, 0.0d), new Vector3d(0.0d, this.checkAreaWidth / 2.0d, 0.0d), new Vector3d(0.0d, (-this.checkAreaWidth) / 2.0d, 0.0d), new Vector3d(this.checkAreaHeight, (-this.checkAreaWidth) / 2.0d, 0.0d)};

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ShowNoiseDetectActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity$DrawNoiseType;", "", "(Ljava/lang/String;I)V", "All", "Lidar", "RGBD", "Mix", "LowObs", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum DrawNoiseType {
        All,
        Lidar,
        RGBD,
        Mix,
        LowObs
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DrawNoiseType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[DrawNoiseType.Mix.ordinal()] = 1;
            $EnumSwitchMapping$0[DrawNoiseType.All.ordinal()] = 2;
            $EnumSwitchMapping$0[DrawNoiseType.Lidar.ordinal()] = 3;
            $EnumSwitchMapping$0[DrawNoiseType.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[DrawNoiseType.values().length];
            $EnumSwitchMapping$1[DrawNoiseType.Lidar.ordinal()] = 1;
            $EnumSwitchMapping$1[DrawNoiseType.All.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[DrawNoiseType.values().length];
            $EnumSwitchMapping$2[DrawNoiseType.RGBD.ordinal()] = 1;
            $EnumSwitchMapping$2[DrawNoiseType.All.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[DrawNoiseType.values().length];
            $EnumSwitchMapping$3[DrawNoiseType.LowObs.ordinal()] = 1;
            $EnumSwitchMapping$3[DrawNoiseType.All.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[ObsType.values().length];
            $EnumSwitchMapping$4[ObsType.Free.ordinal()] = 1;
            $EnumSwitchMapping$4[ObsType.Mix.ordinal()] = 2;
            $EnumSwitchMapping$4[ObsType.Lidar.ordinal()] = 3;
            $EnumSwitchMapping$4[ObsType.RGBD.ordinal()] = 4;
            $EnumSwitchMapping$4[ObsType.LowObs.ordinal()] = 5;
            $EnumSwitchMapping$4[ObsType.OutsideObs.ordinal()] = 6;
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

    public static final /* synthetic */ MediaPlayer access$getMediaPlayer$p(ShowNoiseDetectActivity showNoiseDetectActivity) {
        MediaPlayer mediaPlayer = showNoiseDetectActivity.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        return mediaPlayer;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ShowNoiseDetectActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity$ObsType;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "Free", "Mix", "Lidar", "RGBD", "LowObs", "OutsideObs", "Companion", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ObsType {
        Free(0),
        Mix(1),
        Lidar(2),
        RGBD(3),
        LowObs(4),
        OutsideObs(255);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final int value;

        ObsType(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: ShowNoiseDetectActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity$ObsType$Companion;", "", "()V", "from", "Lcom/pudutech/mirsdk/mircore/ui/ShowNoiseDetectActivity$ObsType;", "findValue", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final ObsType from(int findValue) {
                for (ObsType obsType : ObsType.values()) {
                    if (obsType.getValue() == findValue) {
                        return obsType;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5224R.layout.activity_show_noise_detect);
        this.obsToShow = DrawNoiseType.All;
        ((Button) _$_findCachedViewById(C5224R.id.back_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Job job;
                job = ShowNoiseDetectActivity.this.getResultJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                ShowNoiseDetectActivity.this.obsToShow = ShowNoiseDetectActivity.DrawNoiseType.All;
                ShowNoiseDetectActivity.this.stopPlayer();
                Costmap.INSTANCE.setNoiseDetectSwitch(false, new Vector3d[0]);
                ShowNoiseDetectActivity.this.finish();
            }
        });
        ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).setOnLayoutDrawDone(new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ShowNoiseDetectActivity.this.setListener();
            }
        });
        this.mediaPlayer = new MediaPlayer();
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(3);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setAudioAttributes(builder.build());
        this.mSwitch = (Switch) findViewById(C5224R.id.alert_switch);
        Switch r3 = this.mSwitch;
        if (r3 != null) {
            r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$onCreate$3
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    String str;
                    String str2;
                    if (z) {
                        ShowNoiseDetectActivity.this.openVoice = true;
                        str2 = ShowNoiseDetectActivity.this.TAG;
                        Pdlog.m3273d(str2, "openVoice");
                    } else {
                        if (z) {
                            return;
                        }
                        ShowNoiseDetectActivity.this.openVoice = false;
                        str = ShowNoiseDetectActivity.this.TAG;
                        Pdlog.m3273d(str, "offVoice");
                    }
                }
            });
        }
        View findViewById = findViewById(C5224R.id.check_width);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        this.mCheckWidth = (EditText) findViewById;
        EditText editText = this.mCheckWidth;
        if (editText == null) {
            Intrinsics.throwNpe();
        }
        editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$onCreate$4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable p0) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                double d;
                double d2;
                double d3;
                double d4;
                double d5;
                double d6;
                double d7;
                double d8;
                Vector3d[] vector3dArr;
                Double doubleOrNull = StringsKt.toDoubleOrNull(String.valueOf(p0));
                if (doubleOrNull == null || doubleOrNull.doubleValue() <= 0.0d) {
                    return;
                }
                double doubleValue = doubleOrNull.doubleValue();
                d = ShowNoiseDetectActivity.this.widthUpper;
                if (doubleValue <= d) {
                    d2 = ShowNoiseDetectActivity.this.checkAreaWidth;
                    if (!Intrinsics.areEqual(doubleOrNull, d2)) {
                        ShowNoiseDetectActivity.this.checkAreaWidth = doubleOrNull.doubleValue();
                        ShowNoiseDetectActivity showNoiseDetectActivity = ShowNoiseDetectActivity.this;
                        d3 = showNoiseDetectActivity.checkAreaHeight;
                        d4 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d5 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d6 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d7 = ShowNoiseDetectActivity.this.checkAreaHeight;
                        d8 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        showNoiseDetectActivity.area = new Vector3d[]{new Vector3d(d3, d4 / 2.0d, 0.0d), new Vector3d(0.0d, d5 / 2.0d, 0.0d), new Vector3d(0.0d, (-d6) / 2.0d, 0.0d), new Vector3d(d7, (-d8) / 2.0d, 0.0d)};
                        Costmap costmap = Costmap.INSTANCE;
                        vector3dArr = ShowNoiseDetectActivity.this.area;
                        costmap.setNoiseDetectSwitch(true, vector3dArr);
                    }
                }
            }
        });
        View findViewById2 = findViewById(C5224R.id.check_height);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        this.mCheckHeigth = (EditText) findViewById2;
        EditText editText2 = this.mCheckHeigth;
        if (editText2 == null) {
            Intrinsics.throwNpe();
        }
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$onCreate$5
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable p0) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                double d;
                double d2;
                double d3;
                double d4;
                double d5;
                double d6;
                double d7;
                double d8;
                Vector3d[] vector3dArr;
                Double doubleOrNull = StringsKt.toDoubleOrNull(String.valueOf(p0));
                if (doubleOrNull == null || doubleOrNull.doubleValue() <= 0.0d) {
                    return;
                }
                double doubleValue = doubleOrNull.doubleValue();
                d = ShowNoiseDetectActivity.this.heightUpper;
                if (doubleValue <= d) {
                    d2 = ShowNoiseDetectActivity.this.checkAreaHeight;
                    if (!Intrinsics.areEqual(doubleOrNull, d2)) {
                        ShowNoiseDetectActivity.this.checkAreaHeight = doubleOrNull.doubleValue();
                        ShowNoiseDetectActivity showNoiseDetectActivity = ShowNoiseDetectActivity.this;
                        d3 = showNoiseDetectActivity.checkAreaHeight;
                        d4 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d5 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d6 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        d7 = ShowNoiseDetectActivity.this.checkAreaHeight;
                        d8 = ShowNoiseDetectActivity.this.checkAreaWidth;
                        showNoiseDetectActivity.area = new Vector3d[]{new Vector3d(d3, d4 / 2.0d, 0.0d), new Vector3d(0.0d, d5 / 2.0d, 0.0d), new Vector3d(0.0d, (-d6) / 2.0d, 0.0d), new Vector3d(d7, (-d8) / 2.0d, 0.0d)};
                        Costmap costmap = Costmap.INSTANCE;
                        vector3dArr = ShowNoiseDetectActivity.this.area;
                        costmap.setNoiseDetectSwitch(true, vector3dArr);
                    }
                }
            }
        });
    }

    public final void setListener() {
        Job launch$default;
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ShowNoiseDetectActivity$setListener$1(this, booleanRef, null), 3, null);
        this.getResultJob = launch$default;
    }

    public final void drawAxis() {
        DrawNoiseDetectView noiseDetect = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect, "noiseDetect");
        float width = noiseDetect.getWidth() / 2.0f;
        DrawNoiseDetectView noiseDetect2 = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect2, "noiseDetect");
        float height = noiseDetect2.getHeight() / 4.0f;
        ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas().drawColor(ViewCompat.MEASURED_STATE_MASK);
        ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setStrokeWidth(2.0f);
        ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter().setStyle(Paint.Style.STROKE);
        Canvas mCanvas = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        DrawNoiseDetectView noiseDetect3 = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect3, "noiseDetect");
        float height2 = noiseDetect3.getHeight();
        Paint painter = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter();
        painter.setColor(SupportMenu.CATEGORY_MASK);
        mCanvas.drawLine(width, 0.0f, width, height2, painter);
        Canvas mCanvas2 = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        DrawNoiseDetectView noiseDetect4 = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect4, "noiseDetect");
        mCanvas2.drawLine(0.0f, height, noiseDetect4.getWidth(), height, ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
        ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas().drawCircle(width, height, 25.0f, ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
    }

    public final void drawCheckArea() {
        DrawNoiseDetectView noiseDetect = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect, "noiseDetect");
        DrawNoiseDetectView noiseDetect2 = (DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect);
        Intrinsics.checkExpressionValueIsNotNull(noiseDetect2, "noiseDetect");
        float height = noiseDetect2.getHeight() / 4.0f;
        double d = (this.scale * 1) / 0.05d;
        Canvas mCanvas = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        double width = noiseDetect.getWidth() / 2.0f;
        double d2 = this.checkAreaWidth;
        double d3 = 2;
        Paint painter = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter();
        painter.setColor(InputDeviceCompat.SOURCE_ANY);
        mCanvas.drawLine((float) (width - ((d2 / d3) * d)), height, (float) (((d2 / d3) * d) + width), height, painter);
        Canvas mCanvas2 = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        double d4 = this.checkAreaWidth;
        double d5 = height;
        mCanvas2.drawLine((float) (width - ((d4 / d3) * d)), height, (float) (width - ((d4 / d3) * d)), (float) ((this.checkAreaHeight * d) + d5), ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
        Canvas mCanvas3 = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        double d6 = this.checkAreaWidth;
        double d7 = this.checkAreaHeight;
        mCanvas3.drawLine((float) (width - ((d6 / d3) * d)), (float) (d5 + (d7 * d)), (float) (((d6 / d3) * d) + width), (float) (d5 + (d7 * d)), ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
        Canvas mCanvas4 = ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getMCanvas();
        double d8 = this.checkAreaWidth;
        mCanvas4.drawLine((float) (((d8 / d3) * d) + width), height, (float) (width + ((d8 / d3) * d)), (float) (d5 + (this.checkAreaHeight * d)), ((DrawNoiseDetectView) _$_findCachedViewById(C5224R.id.noiseDetect)).getPainter());
    }

    public final void onRadioButtonClicked(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (v instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) v;
            boolean isChecked = radioButton.isChecked();
            int id = radioButton.getId();
            if (id == C5224R.id.radio_all) {
                if (isChecked) {
                    this.obsToShow = DrawNoiseType.All;
                    Pdlog.m3273d(this.TAG, "show all noise");
                    return;
                }
                return;
            }
            if (id == C5224R.id.radio_lidar) {
                if (isChecked) {
                    this.obsToShow = DrawNoiseType.Lidar;
                    Pdlog.m3273d(this.TAG, "show lidar noise");
                    return;
                }
                return;
            }
            if (id == C5224R.id.radio_rgbd) {
                if (isChecked) {
                    this.obsToShow = DrawNoiseType.RGBD;
                    Pdlog.m3273d(this.TAG, "show rgbd noise");
                    return;
                }
                return;
            }
            if (id == C5224R.id.radio_mix) {
                if (isChecked) {
                    this.obsToShow = DrawNoiseType.Mix;
                    Pdlog.m3273d(this.TAG, "show mix noise");
                    return;
                }
                return;
            }
            if (id == C5224R.id.radio_low_obs && isChecked) {
                this.obsToShow = DrawNoiseType.LowObs;
                Pdlog.m3273d(this.TAG, "show low obs noise");
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Job job = this.getResultJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        stopPlayer();
    }

    public final void stopPlayer() {
        Pdlog.m3273d(this.TAG, "stopPlayer ");
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer.setVolume(0.0f, 0.0f);
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.stop();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e);
        }
    }

    public final void playMusic(final String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.mirsdk.mircore.ui.ShowNoiseDetectActivity$playMusic$1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                String str;
                str = ShowNoiseDetectActivity.this.TAG;
                Pdlog.m3273d(str, "OnCompletion ");
                ShowNoiseDetectActivity.this.playMusic(name);
            }
        });
        try {
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.setVolume(0.4f, 0.4f);
            Pdlog.m3273d(this.TAG, "play music");
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            if (mediaPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer3.reset();
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer4.setDataSource(getAssets().openFd(name));
            MediaPlayer mediaPlayer5 = this.mediaPlayer;
            if (mediaPlayer5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer5.prepare();
            MediaPlayer mediaPlayer6 = this.mediaPlayer;
            if (mediaPlayer6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer6.start();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e);
        }
    }
}
