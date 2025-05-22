package com.pudutech.mirsdk.mircore.p057ui;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationStatus;
import com.pudutech.mirsdk.mircore.tools.Angles;
import com.pudutech.mirsdk.mircore.tools.EncodeUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.io.FileTreeWalk;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bJ$\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010%j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`&2\b\u0010'\u001a\u0004\u0018\u00010\u0004J1\u0010(\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010\b2\b\u0010,\u001a\u0004\u0018\u00010\b2\u0006\u0010-\u001a\u00020\b¢\u0006\u0002\u0010.J\u0012\u0010/\u001a\u00020\u001b2\b\u00100\u001a\u0004\u0018\u000101H\u0014J\u000e\u00102\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/TestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "autoChargeJob", "Lkotlinx/coroutines/Job;", "chargeDist", "", "chargeX", "chargeY", "chargeZ", "cntTime", "", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "getChargeSignal", "", "getRunTimes", "hardwareService", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "mapDir", "mapFile", "runTimes", "wheelError", "autoCharge", "", "v", "Landroid/view/View;", "autoChargePause", "autoChargeSignal", "calAlignPoint", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "pose", "dist", "getMapFiles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", MapElement.Key.DIR, "isFakeReachPile", "navigation_status", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;", "linear_vel", "angular_vel", "target_yaw", "(Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;Ljava/lang/Double;Ljava/lang/Double;D)Z", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openCore", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class TestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job autoChargeJob;
    private double chargeX;
    private double chargeY;
    private double chargeZ;
    private boolean getChargeSignal;
    private boolean getRunTimes;
    private String mapFile;
    private boolean wheelError;
    private final String TAG = "TestActivity";
    private final String mapDir = "/sdcard/pudu/map/";
    private double chargeDist = 0.5d;
    private int runTimes = 1;
    private int cntTime = 1;
    private final AIDLConnection<MirCoreInterface> coreService = new AIDLConnection<>("com.pudutech.mirsdk.mircore.MirCoreService", TestActivity$coreService$1.INSTANCE, null, 4, null);
    private final AIDLConnection<HardwareInterface> hardwareService = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", TestActivity$hardwareService$1.INSTANCE, null, 4, null);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CoreInitState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[CoreInitState.Success.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[CoreInitState.values().length];
            $EnumSwitchMapping$1[CoreInitState.Fail.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[CoreInitStep.values().length];
            $EnumSwitchMapping$2[CoreInitStep.Finish.ordinal()] = 1;
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.ArrayList] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate TestActivity");
        setContentView(C5224R.layout.activity_test);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = getMapFiles(this.mapDir);
        Spinner map_spinner = (Spinner) _$_findCachedViewById(C5224R.id.map_spinner);
        Intrinsics.checkExpressionValueIsNotNull(map_spinner, "map_spinner");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, (ArrayList) objectRef.element);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        map_spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        Spinner map_spinner2 = (Spinner) _$_findCachedViewById(C5224R.id.map_spinner);
        Intrinsics.checkExpressionValueIsNotNull(map_spinner2, "map_spinner");
        map_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$onCreate$2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                Intrinsics.checkParameterIsNotNull(parent, "parent");
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str;
                String str2;
                String str3;
                String str4;
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Intrinsics.checkParameterIsNotNull(view, "view");
                str = TestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("The select map is ");
                ArrayList arrayList = (ArrayList) objectRef.element;
                String str5 = null;
                sb.append(arrayList != null ? (String) arrayList.get(position) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                TestActivity testActivity = TestActivity.this;
                if (((ArrayList) objectRef.element) != null) {
                    StringBuilder sb2 = new StringBuilder();
                    str4 = TestActivity.this.mapDir;
                    sb2.append(str4);
                    EncodeUtils encodeUtils = EncodeUtils.INSTANCE;
                    Object obj = ((ArrayList) objectRef.element).get(position);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "mapList.get(position)");
                    sb2.append(encodeUtils.encodeMapName((String) obj));
                    sb2.append(".pdmap");
                    str5 = sb2.toString();
                }
                testActivity.mapFile = str5;
                str2 = TestActivity.this.TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("The mapfile is ");
                str3 = TestActivity.this.mapFile;
                sb3.append(str3);
                Pdlog.m3273d(str2, sb3.toString());
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TestActivity$onCreate$3(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TestActivity$onCreate$4(this, null), 3, null);
    }

    public final void openCore(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TestActivity$openCore$1(this, null), 3, null);
        Pdlog.m3273d(this.TAG, "finish onCreate");
    }

    public final void autoCharge(View v) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(v, "v");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TestActivity$autoCharge$1(this, null), 3, null);
        this.autoChargeJob = launch$default;
    }

    public final ArrayList<String> getMapFiles(String dir) {
        boolean exists = new File(dir).exists();
        if (!exists) {
            if (exists) {
                throw new NoWhenBranchMatchedException();
            }
            return null;
        }
        FileTreeWalk walk$default = FilesKt.walk$default(new File(dir), null, 1, null);
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = SequencesKt.filter(SequencesKt.filter(walk$default.maxDepth(1), new Function1<File, Boolean>() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$getMapFiles$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(File file) {
                return Boolean.valueOf(invoke2(file));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(File it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return it2.isFile();
            }
        }), new Function1<File, Boolean>() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$getMapFiles$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(File file) {
                return Boolean.valueOf(invoke2(file));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(File it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return Intrinsics.areEqual(FilesKt.getExtension(it2), "pdmap");
            }
        }).iterator();
        while (it.hasNext()) {
            String name = ((File) it.next()).getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
            arrayList.add(StringsKt.replace$default(name, ".pdmap", "", false, 4, (Object) null));
        }
        if (arrayList.size() == 0) {
            return null;
        }
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.set(i, EncodeUtils.INSTANCE.decodeMapName((String) obj));
            i = i2;
        }
        return arrayList;
    }

    public final void autoChargePause(View v) {
        MirCoreInterface mirCoreInterface;
        NavigationInterface navigator;
        Intrinsics.checkParameterIsNotNull(v, "v");
        Job job = this.autoChargeJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.getRunTimes = false;
        this.runTimes = 1;
        this.getChargeSignal = false;
        AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
        if (aIDLConnection != null && (mirCoreInterface = aIDLConnection.getInterface()) != null && (navigator = mirCoreInterface.getNavigator()) != null) {
            navigator.resetNavigationFlag();
        }
        Pdlog.m3273d(this.TAG, "autoChargePause");
    }

    public final void autoChargeSignal(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.getChargeSignal = true;
        Pdlog.m3273d(this.TAG, "autoChargeSignal");
    }

    public final Vector3d calAlignPoint(Vector3d pose, double dist) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        pose.setX(pose.getX() + (Math.cos(pose.getZ()) * dist));
        pose.setY(pose.getY() + (dist * Math.sin(pose.getZ())));
        pose.setZ(Angles.INSTANCE.normalizeRad(pose.getZ() + 3.141592653589793d));
        return pose;
    }

    public final boolean isFakeReachPile(NavigationStatus navigation_status, Double linear_vel, Double angular_vel, double target_yaw) {
        if (this.runTimes == 1 || navigation_status == null || linear_vel == null || angular_vel == null || navigation_status != NavigationStatus.Navigating || !Intrinsics.areEqual(linear_vel, 0.0d) || !Intrinsics.areEqual(angular_vel, 0.0d) || Math.abs(target_yaw - MirCoreImpl.INSTANCE.getPosition$mircore_packRelease().getZ()) >= 0.3d) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "Fake Reach Pile");
        return true;
    }
}
