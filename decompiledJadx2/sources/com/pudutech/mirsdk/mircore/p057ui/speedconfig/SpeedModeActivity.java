package com.pudutech.mirsdk.mircore.p057ui.speedconfig;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.MirCoreScopeKt;
import com.pudutech.mirsdk.mircore.module.speedlevel.MODE;
import com.pudutech.mirsdk.mircore.module.speedlevel.PlannerParamUtils;
import com.pudutech.mirsdk.sdksafe.SDKSafe;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SpeedModeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/SpeedModeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "editTextAdapter", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;", "nowModeName", "", "onClick", "", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveSpeedMode", "selectedMode", "name", "Companion", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SpeedModeActivity extends AppCompatActivity implements View.OnClickListener {
    private HashMap _$_findViewCache;
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String SHOW_INFO = SHOW_INFO;
    private static final String SHOW_INFO = SHOW_INFO;
    private static final String EDIT_INFO = EDIT_INFO;
    private static final String EDIT_INFO = EDIT_INFO;
    private String nowModeName = "";
    private final EditTextAdapter editTextAdapter = new EditTextAdapter(this);

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5224R.layout.activity_speed_mode);
        ListView listConfigure = (ListView) _$_findCachedViewById(C5224R.id.listConfigure);
        Intrinsics.checkExpressionValueIsNotNull(listConfigure, "listConfigure");
        listConfigure.setAdapter((ListAdapter) this.editTextAdapter);
        String str = SHOW_INFO + " 巡航\u3000" + PlannerParamUtils.INSTANCE.getCruise_Mode() + "  直达 " + PlannerParamUtils.INSTANCE.getP2P_Mode() + "   返航 " + PlannerParamUtils.INSTANCE.getGohome_Mode();
        TextView tvSpeedMode = (TextView) _$_findCachedViewById(C5224R.id.tvSpeedMode);
        Intrinsics.checkExpressionValueIsNotNull(tvSpeedMode, "tvSpeedMode");
        tvSpeedMode.setText(str);
        selectedMode(PlannerParamUtils.INSTANCE.getCruise_Mode());
        SpeedModeActivity speedModeActivity = this;
        ((Button) _$_findCachedViewById(C5224R.id.btnSlowest)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnSlower)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnSlow)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnNormal)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnFast)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnFaster)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnFastest)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnFlyup)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnCancel)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnSave)).setOnClickListener(speedModeActivity);
        ((Button) _$_findCachedViewById(C5224R.id.btnFactoryReset)).setOnClickListener(speedModeActivity);
        if (MirCoreImpl.INSTANCE.getMachineType() == MachineModel.Ninetales) {
            Button btnTortoise = (Button) _$_findCachedViewById(C5224R.id.btnTortoise);
            Intrinsics.checkExpressionValueIsNotNull(btnTortoise, "btnTortoise");
            btnTortoise.setVisibility(0);
            Button btnLaziest = (Button) _$_findCachedViewById(C5224R.id.btnLaziest);
            Intrinsics.checkExpressionValueIsNotNull(btnLaziest, "btnLaziest");
            btnLaziest.setVisibility(0);
            Button btnLazier = (Button) _$_findCachedViewById(C5224R.id.btnLazier);
            Intrinsics.checkExpressionValueIsNotNull(btnLazier, "btnLazier");
            btnLazier.setVisibility(0);
            Button btnLazy = (Button) _$_findCachedViewById(C5224R.id.btnLazy);
            Intrinsics.checkExpressionValueIsNotNull(btnLazy, "btnLazy");
            btnLazy.setVisibility(0);
            ((Button) _$_findCachedViewById(C5224R.id.btnTortoise)).setOnClickListener(speedModeActivity);
            ((Button) _$_findCachedViewById(C5224R.id.btnLaziest)).setOnClickListener(speedModeActivity);
            ((Button) _$_findCachedViewById(C5224R.id.btnLazier)).setOnClickListener(speedModeActivity);
            ((Button) _$_findCachedViewById(C5224R.id.btnLazy)).setOnClickListener(speedModeActivity);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("speed mode view ");
        sb.append(v != null ? Integer.valueOf(v.getId()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        int i = C5224R.id.btnSlowest;
        if (valueOf != null && valueOf.intValue() == i) {
            selectedMode(MODE.Slowest.name());
            return;
        }
        int i2 = C5224R.id.btnSlower;
        if (valueOf != null && valueOf.intValue() == i2) {
            selectedMode(MODE.Slower.name());
            return;
        }
        int i3 = C5224R.id.btnSlow;
        if (valueOf != null && valueOf.intValue() == i3) {
            selectedMode(MODE.Slow.name());
            return;
        }
        int i4 = C5224R.id.btnNormal;
        if (valueOf != null && valueOf.intValue() == i4) {
            selectedMode(MODE.Normal.name());
            return;
        }
        int i5 = C5224R.id.btnFast;
        if (valueOf != null && valueOf.intValue() == i5) {
            selectedMode(MODE.Fast.name());
            return;
        }
        int i6 = C5224R.id.btnFaster;
        if (valueOf != null && valueOf.intValue() == i6) {
            selectedMode(MODE.Faster.name());
            return;
        }
        int i7 = C5224R.id.btnFastest;
        if (valueOf != null && valueOf.intValue() == i7) {
            selectedMode(MODE.Fastest.name());
            return;
        }
        int i8 = C5224R.id.btnFlyup;
        if (valueOf != null && valueOf.intValue() == i8) {
            selectedMode(MODE.Flyup.name());
            return;
        }
        int i9 = C5224R.id.btnTortoise;
        if (valueOf != null && valueOf.intValue() == i9) {
            selectedMode(MODE.Tortoise.name());
            return;
        }
        int i10 = C5224R.id.btnLaziest;
        if (valueOf != null && valueOf.intValue() == i10) {
            selectedMode(MODE.Laziest.name());
            return;
        }
        int i11 = C5224R.id.btnLazier;
        if (valueOf != null && valueOf.intValue() == i11) {
            selectedMode(MODE.Lazier.name());
            return;
        }
        int i12 = C5224R.id.btnLazy;
        if (valueOf != null && valueOf.intValue() == i12) {
            selectedMode(MODE.Lazy.name());
            return;
        }
        int i13 = C5224R.id.btnCancel;
        if (valueOf != null && valueOf.intValue() == i13) {
            finish();
            return;
        }
        int i14 = C5224R.id.btnSave;
        if (valueOf != null && valueOf.intValue() == i14) {
            saveSpeedMode();
            return;
        }
        int i15 = C5224R.id.btnFactoryReset;
        if (valueOf != null && valueOf.intValue() == i15) {
            SDKSafe.INSTANCE.checkControlAuth(this, "vrfac", new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$onClick$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: Classes with same name are omitted:
                  classes4.dex
                 */
                /* compiled from: SpeedModeActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$onClick$1$1", m3970f = "SpeedModeActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$onClick$1$1 */
                /* loaded from: classes6.dex */
                public static final class C52711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6290p$;

                    C52711(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C52711 c52711 = new C52711(completion);
                        c52711.f6290p$ = (CoroutineScope) obj;
                        return c52711;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C52711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6290p$;
                        SpeedModeActivity speedModeActivity = SpeedModeActivity.this;
                        str = SpeedModeActivity.this.nowModeName;
                        speedModeActivity.selectedMode(str);
                        return Unit.INSTANCE;
                    }
                }

                public final void invoke(int i16) {
                    String str2;
                    if (i16 != 0) {
                        return;
                    }
                    PlannerParamUtils plannerParamUtils = PlannerParamUtils.INSTANCE;
                    str2 = SpeedModeActivity.this.nowModeName;
                    plannerParamUtils.factoryReset(str2);
                    BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), Dispatchers.getMain(), null, new C52711(null), 2, null);
                }
            });
        }
    }

    private final void saveSpeedMode() {
        SDKSafe.INSTANCE.checkControlAuth(this, "svpm", new SpeedModeActivity$saveSpeedMode$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectedMode(String name) {
        this.editTextAdapter.selectedMode$mircore_packRelease(PlannerParamUtils.INSTANCE.getSpecifiedMode(name));
        this.editTextAdapter.notifyDataSetChanged();
        String str = EDIT_INFO + ' ' + name;
        TextView textView = (TextView) _$_findCachedViewById(C5224R.id.tvEditingSpeedMode);
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setText(str);
        this.nowModeName = name;
        Pdlog.m3273d(TAG, "nowModeName: " + this.nowModeName);
    }
}
